package org.taskmanager.user_service.core.converters;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.taskmanager.user_service.core.dto.base.UserDTO;
import org.taskmanager.user_service.core.dto.base.UserPageDTO;
import org.taskmanager.user_service.dao.entity.User;

public class UserPageToUserPageDTOConverter implements Converter<Page<User>, UserPageDTO> {
    private final ConversionService conversionService;

    public UserPageToUserPageDTOConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public UserPageDTO convert(Page<User> userPage) {
        return new UserPageDTO(userPage.getNumber(), userPage.getSize(), userPage.getTotalPages(),
                userPage.getTotalElements(), userPage.isFirst(), userPage.getNumberOfElements(),
                userPage.isLast(), userPage.get().map(u -> this.conversionService.convert(u, UserDTO.class)).toList());
    }
}
