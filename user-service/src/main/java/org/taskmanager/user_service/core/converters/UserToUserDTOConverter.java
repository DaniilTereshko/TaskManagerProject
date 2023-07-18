package org.taskmanager.user_service.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.taskmanager.user_service.core.dto.base.UserDTO;
import org.taskmanager.user_service.dao.entity.User;

import java.time.ZoneOffset;

public class UserToUserDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO(user.getUuid(), user.getFio(), user.getEmail(), user.getUserRole().name(), user.getUserStatus().name());
        if(user.getCreateDate() != null){
            userDTO.setCreateDate(user.getCreateDate().toInstant(ZoneOffset.UTC).toEpochMilli());
        }
        if(user.getUpdateDate() != null){
            userDTO.setUpdateDate(user.getUpdateDate().toInstant(ZoneOffset.UTC).toEpochMilli());
        }
        return userDTO;
    }

}
