package org.taskmanager.task_server.service.implemetation.task;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.taskmanager.audit_client.core.enums.EssenceType;
import org.taskmanager.task_client.core.dto.base.UserRefDTO;
import org.taskmanager.task_client.core.dto.create.ProjectCreateDTO;
import org.taskmanager.task_client.core.dto.update.ProjectUpdateDTO;
import org.taskmanager.task_client.core.enums.ProjectStatus;
import org.taskmanager.task_server.core.exception.IncorrectDataException;
import org.taskmanager.task_server.core.exception.InvalidEmployeeException;
import org.taskmanager.task_server.core.exception.NotFoundException;
import org.taskmanager.task_server.core.exception.VersionsMatchException;
import org.taskmanager.task_server.dao.entity.Project;
import org.taskmanager.task_server.dao.repositories.IProjectRepository;
import org.taskmanager.task_server.service.api.audit.IAuditService;
import org.taskmanager.task_server.service.api.task.IProjectService;
import org.taskmanager.task_server.service.api.user.IUserService;
import org.taskmanager.user_client.core.dto.base.UserDTO;
import org.taskmanager.user_client.core.enums.UserRole;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ProjectService implements IProjectService {
    private static final String VERSIONS_MATCH_ERROR = "Версии не совпадают";
    private static final String INVALID_MANAGER = "Ошибка назначения пользователя на должность менеджера";
    private static final String INVALID_STAFF = "Ошибка назначения персонала на проект";
    private static final String PROJECT_NOT_FOUND = "Проект не найден";
    private static final String INCORRECT_DATA = "Неверные данные. Попробуйте еще раз";
    private static final String PROJECT_IS_SAVED = "Проект сохранена";
    private static final String PROJECT_IS_UPDATED = "Проект обновлена";
    private final IUserService userService;
    private final IProjectRepository projectRepository;
    private final IAuditService auditService;
    private final Validator validator;

    public ProjectService(IUserService userService, IProjectRepository projectRepository, IAuditService auditService, Validator validator) {
        this.userService = userService;
        this.projectRepository = projectRepository;
        this.auditService = auditService;
        this.validator = validator;
    }
    @Transactional
    @Override
    public Project save(ProjectCreateDTO item) {
        this.validate(item);
        this.validateManager(item.getManager());
        this.validateStaff(item.getStaff());
        Project project = new Project();
        project.setUuid(UUID.randomUUID());
        project.setManager(item.getManager().getUuid());
        project.setName(item.getName());
        project.setDescription(item.getDescription());
        project.setStatus(item.getStatus());
        item.getStaff().forEach(u -> project.addUser(u.getUuid()));
        Project savedProject = this.projectRepository.saveAndFlush(project);
        this.auditService.save(PROJECT_IS_SAVED, savedProject.getUuid().toString(), EssenceType.PROJECT);
        return savedProject;
    }
    @Transactional(readOnly = true)
    @Override
    public Page<Project> get(int page, int size, boolean archived) {
        if(page < 0 || size < 0){
            throw new IncorrectDataException(INCORRECT_DATA);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        if(!archived){
            return this.projectRepository.findAllByStatus(pageRequest, ProjectStatus.ACTIVE);
        }
        return this.projectRepository.findAll(pageRequest);
    }
    @Transactional(readOnly = true)
    @Override
    public Project get(UUID uuid) {
        return this.projectRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(PROJECT_NOT_FOUND));
    }
    @Transactional
    @Override
    public Project update(ProjectUpdateDTO item) {
        this.validate(item);
        this.validateManager(item.getManager());
        this.validateStaff(item.getStaff());
        Project currentProject = this.get(item.getUuid());
        if (!item.getUpdateDate().isEqual(currentProject.getUpdateDate())) {
            throw new VersionsMatchException(VERSIONS_MATCH_ERROR);
        }
        currentProject.setName(item.getName());
        currentProject.setDescription(item.getDescription());
        currentProject.setStatus(item.getStatus());
        currentProject.setManager(item.getManager().getUuid());
        item.getStaff().forEach(u -> currentProject.addUser(u.getUuid()));
        Project updatedProject = this.projectRepository.saveAndFlush(currentProject);
        this.auditService.save(PROJECT_IS_UPDATED, currentProject.getUuid().toString(), EssenceType.PROJECT);
        return updatedProject;
    }
    @Transactional(readOnly = true)
    @Override
    public List<Project> findAllByUser(UUID uuid) {
        return this.projectRepository.findAllByStaffContains(uuid);
    }

    private void validateStaff(Set<UserRefDTO> staff){
        List<UserDTO> existingUsers = this.userService.get(staff);
        if(existingUsers.size() != staff.size()){
            throw new InvalidEmployeeException(INVALID_STAFF, "staff");
        }
    }
    private void validateManager(UserRefDTO manager){
        UserDTO dto = this.userService.get(manager);
        if(dto == null || !dto.getRole().equals(UserRole.MANAGER)){
            throw new InvalidEmployeeException(INVALID_MANAGER, "manager");
        }
    }

    private <T> void validate(T item){
        Set<ConstraintViolation<T>> constraintViolations = this.validator.validate(item);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
