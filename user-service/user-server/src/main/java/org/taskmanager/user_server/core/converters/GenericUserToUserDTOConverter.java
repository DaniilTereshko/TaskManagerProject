package org.taskmanager.user_server.core.converters;

import com.google.common.collect.ImmutableSet;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.taskmanager.user_client.core.dto.base.UserDTO;
import org.taskmanager.user_client.core.dto.update.UserUpdateDTO;
import org.taskmanager.user_server.core.exception.ConversionTypeException;
import org.taskmanager.user_server.dao.entity.User;

import java.time.ZoneOffset;
import java.util.Set;

public class GenericUserToUserDTOConverter implements GenericConverter {
    @Override
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        GenericConverter.ConvertiblePair[] pairs = new GenericConverter.ConvertiblePair[] {
                new GenericConverter.ConvertiblePair(User.class, UserDTO.class),
                new GenericConverter.ConvertiblePair(User.class, UserUpdateDTO.class)};
        return ImmutableSet.copyOf(pairs);
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (sourceType.getType() == UserDTO.class){
            return source;
        }
        if(targetType.getType() == UserDTO.class){
            User user = (User) source;
            UserDTO userDTO = new UserDTO(user.getUuid(), user.getFio(), user.getEmail(), user.getRole(), user.getStatus());
            if(user.getCreateDate() != null){
                userDTO.setCreateDate(user.getCreateDate().toInstant(ZoneOffset.UTC).toEpochMilli());
            }
            if(user.getUpdateDate() != null){
                userDTO.setUpdateDate(user.getUpdateDate().toInstant(ZoneOffset.UTC).toEpochMilli());
            }
            return userDTO;
        }
        if(targetType.getType() == UserUpdateDTO.class){
            User user = (User) source;
            return new UserUpdateDTO(user.getUuid(), user.getFio(), user.getEmail(), user.getRole(), user.getStatus(), user.getPassword(), user.getUpdateDate());
        }
        return new ConversionTypeException("Ошибка конвертации из entity в DTO");
    }

}
