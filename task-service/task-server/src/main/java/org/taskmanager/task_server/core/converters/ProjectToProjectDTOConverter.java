package org.taskmanager.task_server.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.taskmanager.task_client.core.dto.base.ProjectDTO;
import org.taskmanager.task_client.core.dto.base.UserRefDTO;
import org.taskmanager.task_server.dao.entity.Project;

import java.time.ZoneOffset;
import java.util.stream.Collectors;

public class ProjectToProjectDTOConverter implements Converter<Project, ProjectDTO> {
    @Override
    public ProjectDTO convert(Project project) {
        return new ProjectDTO(project.getUuid(), project.getName(), project.getDescription(),
                new UserRefDTO(project.getManager()), project.getStaff().stream().map(UserRefDTO::new).collect(Collectors.toSet()),project.getStatus(),
                project.getCreateDate().toInstant(ZoneOffset.UTC).toEpochMilli(), project.getUpdateDate().toInstant(ZoneOffset.UTC).toEpochMilli());
    }
}
