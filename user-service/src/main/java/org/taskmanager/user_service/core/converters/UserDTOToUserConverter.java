package org.taskmanager.user_service.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.taskmanager.user_service.core.dto.base.UserDTO;
import org.taskmanager.user_service.core.enums.UserRole;
import org.taskmanager.user_service.core.enums.UserStatus;
import org.taskmanager.user_service.dao.entity.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class UserDTOToUserConverter implements Converter<UserDTO, User> {

    @Override
    public User convert(UserDTO userDTO) {
        User user = new User(userDTO.getUuid(), userDTO.getFio(), userDTO.getMail());
        if(userDTO.getRole() != null){
            user.setUserRole(UserRole.valueOf(userDTO.getRole()));
        }
        if(userDTO.getStatus() != null){
            user.setUserStatus(UserStatus.valueOf(userDTO.getStatus()));
        }
        if (userDTO.getCreateDate() != null){
            user.setCreateDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(userDTO.getCreateDate()), ZoneOffset.UTC));
        }
        if(userDTO.getUpdateDate() != null){
            user.setUpdateDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(userDTO.getUpdateDate()), ZoneOffset.UTC));
        }
        return user;
    }
}
