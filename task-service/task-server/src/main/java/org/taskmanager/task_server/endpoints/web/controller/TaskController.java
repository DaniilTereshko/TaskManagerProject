package org.taskmanager.task_server.endpoints.web.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.base_package.dto.PageDTO;
import org.taskmanager.task_client.core.dto.base.*;
import org.taskmanager.task_client.core.dto.create.TaskCreateDTO;
import org.taskmanager.task_client.core.dto.update.PatchTaskStatusDTO;
import org.taskmanager.task_client.core.dto.update.TaskUpdateDTO;
import org.taskmanager.task_client.core.enums.TaskStatus;
import org.taskmanager.task_server.dao.entity.Project;
import org.taskmanager.task_server.dao.entity.Task;
import org.taskmanager.task_server.service.api.task.ITaskService;
import org.taskmanager.task_server.service.util.PageConverter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final ITaskService taskService;
    private final ConversionService conversionService;
    private final PageConverter pageConverter;

    public TaskController(ITaskService taskService, ConversionService conversionService, PageConverter pageConverter) {
        this.taskService = taskService;
        this.conversionService = conversionService;
        this.pageConverter = pageConverter;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskDTO> save(@RequestBody TaskCreateDTO taskCreateDTO){
        Task task = this.taskService.save(taskCreateDTO);
        TaskDTO dto = this.conversionService.convert(task, TaskDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<PageDTO<TaskDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) List<TaskStatus> status,
            @RequestParam(required = false) List<ProjectRefDTO> project,
            @RequestParam(required = false) List<UserRefDTO> implementer){
        FilterDTO filterDTO = new FilterDTO(status, project, implementer);
        Page<Task> tasks = this.taskService.get(page, size, filterDTO);
        PageDTO<TaskDTO> pageDTO = this.pageConverter.convertPageToDTO(tasks, TaskDTO.class);
        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TaskDTO> findById(@PathVariable UUID uuid){
        Task task = this.taskService.get(uuid);
        TaskDTO dto = this.conversionService.convert(task, TaskDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<TaskDTO> update(
            @PathVariable UUID uuid,
            @PathVariable("dt_update") LocalDateTime updateDate,
            @RequestBody TaskUpdateDTO userUpdateDTO){
        userUpdateDTO.setUuid(uuid);
        userUpdateDTO.setUpdateDate(updateDate);
        Task task = this.taskService.update(userUpdateDTO);
        TaskDTO dto = this.conversionService.convert(task, TaskDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PatchMapping("/{uuid}/dt_update/{dt_update}/status/{status}")
    public ResponseEntity<TaskDTO> statusPatch(
            @PathVariable UUID uuid,
            @PathVariable("dt_update") LocalDateTime updateDate,
            @PathVariable TaskStatus status){
        PatchTaskStatusDTO patch = new PatchTaskStatusDTO(uuid, updateDate, status);
        Task task = this.taskService.updateStatus(patch);
        TaskDTO dto = this.conversionService.convert(task, TaskDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
