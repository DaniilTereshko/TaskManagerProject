package org.taskmanager.task_server.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.taskmanager.task_client.core.dto.base.ProjectRefDTO;

import java.util.UUID;

public class StringToProjectRefDTOConverter implements Converter<String, ProjectRefDTO> {
    @Override
    public ProjectRefDTO convert(String source) {
        return new ProjectRefDTO(UUID.fromString(source));
    }
}
