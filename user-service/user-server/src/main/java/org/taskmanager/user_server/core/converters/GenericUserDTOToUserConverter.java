package org.taskmanager.user_server.core.converters;

import com.google.common.collect.ImmutableSet;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.taskmanager.user_client.core.dto.create.UserCreateDTO;
import org.taskmanager.user_client.core.dto.update.UserUpdateDTO;
import org.taskmanager.user_server.core.exception.ConversionTypeException;
import org.taskmanager.user_server.dao.entity.User;

import java.util.Set;

public class GenericUserDTOToUserConverter implements GenericConverter {
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        ConvertiblePair[] pairs = new ConvertiblePair[] {
                new ConvertiblePair(UserCreateDTO.class, User.class)};
        return ImmutableSet.copyOf(pairs);
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (sourceType.getType() == User.class){
            return source;
        }
        if(sourceType.getType() == UserCreateDTO.class){
            UserCreateDTO dto = (UserCreateDTO) source;
            return new User(dto.getFio(), dto.getMail(), dto.getPassword(),
                    dto.getRole(), dto.getStatus());
        }
        if(sourceType.getType() == UserUpdateDTO.class){
            UserUpdateDTO dto = (UserUpdateDTO) source;
            return new User(dto.getUuid(),dto.getFio(), dto.getRole(),
                    dto.getStatus(), dto.getMail(), dto.getPassword(),
                    dto.getUpdateDate());
        }
        return new ConversionTypeException("Ошибка конвертации из DTO в entity");
    }
}
