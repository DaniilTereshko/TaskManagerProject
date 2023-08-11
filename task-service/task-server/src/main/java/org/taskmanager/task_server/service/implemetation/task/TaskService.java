package org.taskmanager.task_server.service.implemetation.task;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.taskmanager.audit_client.core.enums.EssenceType;
import org.taskmanager.task_client.core.dto.base.FilterDTO;
import org.taskmanager.task_client.core.dto.base.ProjectRefDTO;
import org.taskmanager.task_client.core.dto.base.UserRefDTO;
import org.taskmanager.task_client.core.dto.create.TaskCreateDTO;
import org.taskmanager.task_client.core.dto.update.PatchTaskStatusDTO;
import org.taskmanager.task_client.core.dto.update.TaskUpdateDTO;
import org.taskmanager.task_client.core.enums.TaskStatus;
import org.taskmanager.task_server.core.exception.*;
import org.taskmanager.task_server.dao.entity.Project;
import org.taskmanager.task_server.dao.entity.Task;
import org.taskmanager.task_server.dao.repositories.ITaskRepository;
import org.taskmanager.task_server.dao.spetifications.TaskSpecifications;
import org.taskmanager.task_server.service.api.audit.IAuditService;
import org.taskmanager.task_server.service.api.task.IProjectService;
import org.taskmanager.task_server.service.api.task.ITaskService;
import org.taskmanager.task_server.service.api.user.IUserService;
import org.taskmanager.task_server.service.implemetation.user.UserHolder;
import org.taskmanager.user_client.core.dto.base.UserDTO;
import org.taskmanager.user_client.core.enums.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TaskService implements ITaskService {
    private static final String VERSIONS_MATCH_ERROR = "Версии не совпадают";
    private static final String TASK_NOT_FOUND = "Задача не найдена";
    private static final String PROJECT_NOT_AVAILABLE = "Проект недоступен";
    private static final String INVALID_IMPLEMENTER = "Указаный исполнитель не работает на этом проекте";
    private static final String INCORRECT_DATA = "Неверные данные. Попробуйте еще раз";
    private static final String TASK_IS_SAVED = "Задача сохранена";
    private static final String TASK_IS_UPDATED = "Задача обновлена";
    private static final String STATUS_IS_UPDATED = "Статус задачи обнавлен";
    private final ITaskRepository taskRepository;
    private final IProjectService projectService;
    private final UserHolder userHolder;
    private final IUserService userService;
    private final IAuditService auditService;
    private final Validator validator;


    public TaskService(ITaskRepository taskRepository, IProjectService projectService, UserHolder userHolder, IUserService userService, IAuditService auditService, Validator validator) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
        this.userHolder = userHolder;
        this.userService = userService;
        this.auditService = auditService;
        this.validator = validator;
    }
    @Transactional
    @Override
    public Task save(TaskCreateDTO item) {
        this.validate(item);
        UserDTO user = this.userHolder.getUser();
        Project project = this.projectService.get(item.getProject().getUuid());
        UserDTO implementer = this.userService.get(item.getImplementer());
        if(!this.isAvailableProject(user.getUuid(), project)){
            throw new ProjectNotAvailableException(PROJECT_NOT_AVAILABLE);
        }
        if(!this.isAvailableProject(implementer.getUuid(), project)){
            throw new InvalidEmployeeException(INVALID_IMPLEMENTER, "implementer");
        }
        Task task = new Task();
        task.setUuid(UUID.randomUUID());
        task.setProject(project);
        task.setDescription(item.getDescription());
        task.setTitle(item.getTitle());
        task.setStatus(item.getStatus());
        task.setImplementer(item.getImplementer().getUuid());
        Task savedTask = this.taskRepository.saveAndFlush(task);
        this.auditService.save(TASK_IS_SAVED, task.getUuid().toString(), EssenceType.TASK);
        return savedTask;
    }
    @Transactional(readOnly = true)
    @Override
    public Page<Task> get(int page, int size, FilterDTO filterDTO) {
        if(page < 0 || size < 0){
            throw new IncorrectDataException(INCORRECT_DATA);
        }
        UserDTO user = this.userHolder.getUser();
        List<ProjectRefDTO> availableProjects = this.getFilteredProjectRefs(user, filterDTO);
        filterDTO.setProject(availableProjects);

        if(filterDTO.getImplementer() == null){
            filterDTO.setImplementer(List.of(new UserRefDTO(user.getUuid())));
        }
        if(filterDTO.getStatus() == null){
            filterDTO.setStatus(List.of(TaskStatus.CLOSE, TaskStatus.BLOCK, TaskStatus.WAIT, TaskStatus.IN_WORK, TaskStatus.DONE));
        }
        Specification<Task> specification = Specification.where(TaskSpecifications.byProject(filterDTO.getProject()))
                .and(TaskSpecifications.byImplementer(filterDTO.getImplementer()))
                .and(TaskSpecifications.byStatus(filterDTO.getStatus()));

        return this.taskRepository.findAll(specification, PageRequest.of(page, size));
    }
    @Transactional(readOnly = true)
    @Override
    public Task get(UUID uuid) {
        UserDTO user = this.userHolder.getUser();
        Task task = this.taskRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(TASK_NOT_FOUND));
        if(!this.isAvailableProject(user.getUuid(), task.getProject())){
            throw new ProjectNotAvailableException(PROJECT_NOT_AVAILABLE);
        }
        return task;
    }
    @Transactional
    @Override
    public Task update(TaskUpdateDTO item) {
        this.validate(item);
        UserDTO user = this.userHolder.getUser();
        Task currentTask = this.get(item.getUuid());
        Project project = this.projectService.get(item.getProject().getUuid());
        UserDTO implementer = this.userService.get(item.getImplementer());
        if(!this.isAvailableProject(user.getUuid(), project)){
            throw new ProjectNotAvailableException(PROJECT_NOT_AVAILABLE);
        }
        if(!this.isAvailableProject(implementer.getUuid(), project)){
            throw new InvalidEmployeeException(INVALID_IMPLEMENTER, "implementer");
        }
        if (!item.getUpdateDate().isEqual(currentTask.getUpdateDate())) {
            throw new VersionsMatchException(VERSIONS_MATCH_ERROR);
        }
        currentTask.setProject(project);
        currentTask.setImplementer(item.getImplementer().getUuid());
        currentTask.setTitle(item.getTitle());
        currentTask.setStatus(item.getStatus());
        currentTask.setDescription(item.getDescription());
        Task updatedTask = this.taskRepository.saveAndFlush(currentTask);
        this.auditService.save(TASK_IS_UPDATED, currentTask.getUuid().toString(), EssenceType.TASK);
        return updatedTask;
    }
    @Transactional
    @Override
    public Task updateStatus(PatchTaskStatusDTO item) {
        UserDTO user = this.userHolder.getUser();
        Task currentTask = this.get(item.getUuid());
        if(!this.isAvailableProject(user.getUuid(), currentTask.getProject())){
            throw new ProjectNotAvailableException(PROJECT_NOT_AVAILABLE);
        }
        if (!item.getUpdateDate().isEqual(currentTask.getUpdateDate())) {
            throw new VersionsMatchException(VERSIONS_MATCH_ERROR);
        }
        currentTask.setStatus(item.getStatus());
        Task updatedTask = this.taskRepository.saveAndFlush(currentTask);
        this.auditService.save(STATUS_IS_UPDATED, currentTask.getUuid().toString(), EssenceType.TASK);
        return updatedTask;
    }

    private boolean isAvailableProject(UUID user, Project project){
        return project.getStaff().contains(user) || project.getManager().equals(user);
    }
    @Transactional(readOnly = true)
    private List<ProjectRefDTO> getFilteredProjectRefs(UserDTO user, FilterDTO filterDTO) {
        if (filterDTO.getProject() == null){
            List<Project> userProjects = this.projectService.findAllByUser(user.getUuid());
            return userProjects.stream().map(p -> new ProjectRefDTO(p.getUuid())).toList();
        }
        if(!user.getRole().equals(UserRole.ADMIN)) {
            List<ProjectRefDTO> availableProjects = new ArrayList<>();
            for (ProjectRefDTO dto : filterDTO.getProject()) {
                Project project = this.projectService.get(dto.getUuid());
                Set<UUID> staff = project.getStaff();
                if (staff.contains(user.getUuid()) || project.getManager().equals(user.getUuid())) {
                    availableProjects.add(dto);
                }
            }
            return availableProjects;
        }
        return filterDTO.getProject();
    }

    private <T> void validate(T item){
        Set<ConstraintViolation<T>> constraintViolations = this.validator.validate(item);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
