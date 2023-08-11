package org.taskmanager.user_server.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.taskmanager.user_client.core.dto.base.UserDetailsDTO;
import org.taskmanager.user_server.dao.entity.User;

import java.util.List;

public class UserToUserDetailsDTOConverter implements Converter<User, UserDetailsDTO> {
    @Override
    public UserDetailsDTO convert(User source) {
        return new UserDetailsDTO(source.getUuid(), source.getFio(), source.getEmail(), source.getPassword(), source.getStatus(), List.of(source.getRole()));
    }
}
