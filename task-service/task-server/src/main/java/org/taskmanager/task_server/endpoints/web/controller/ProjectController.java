package org.taskmanager.task_server.endpoints.web.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.base_package.dto.PageDTO;
import org.taskmanager.task_client.core.dto.base.ProjectDTO;
import org.taskmanager.task_client.core.dto.base.TaskDTO;
import org.taskmanager.task_client.core.dto.create.ProjectCreateDTO;
import org.taskmanager.task_client.core.dto.update.ProjectUpdateDTO;
import org.taskmanager.task_server.dao.entity.Project;
import org.taskmanager.task_server.service.api.task.IProjectService;
import org.taskmanager.task_server.service.util.PageConverter;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final IProjectService projectService;
    private final ConversionService conversionService;
    private final PageConverter pageConverter;

    public ProjectController(IProjectService projectService, ConversionService conversionService, PageConverter pageConverter) {
        this.projectService = projectService;
        this.conversionService = conversionService;
        this.pageConverter = pageConverter;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProjectDTO> save(@RequestBody ProjectCreateDTO projectCreateDTO){
        Project project = this.projectService.save(projectCreateDTO);
        ProjectDTO dto = this.conversionService.convert(project, ProjectDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<PageDTO<ProjectDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "false") boolean archived){
        Page<Project> projects = this.projectService.get(page, size, archived);
        PageDTO<ProjectDTO> projectPageDTO = this.pageConverter.convertPageToDTO(projects, ProjectDTO.class);
        return new ResponseEntity<>(projectPageDTO, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProjectDTO> findById(@PathVariable UUID uuid){
        Project project = this.projectService.get(uuid);
        ProjectDTO dto = this.conversionService.convert(project, ProjectDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<ProjectDTO> update(
            @PathVariable UUID uuid,
            @PathVariable("dt_update") LocalDateTime updateDate,
            @RequestBody ProjectUpdateDTO projectUpdateDTO){
        projectUpdateDTO.setUpdateDate(updateDate);
        projectUpdateDTO.setUuid(uuid);
        Project project = this.projectService.update(projectUpdateDTO);
        ProjectDTO dto = this.conversionService.convert(project, ProjectDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
