package org.taskmanager.task_server.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.taskmanager.task_client.core.dto.base.UserRefDTO;

import java.util.UUID;

public class StringToUserRefDTOConverter implements Converter<String, UserRefDTO> {
    @Override
    public UserRefDTO convert(String source) {
        return new UserRefDTO(UUID.fromString(source));
    }
}
