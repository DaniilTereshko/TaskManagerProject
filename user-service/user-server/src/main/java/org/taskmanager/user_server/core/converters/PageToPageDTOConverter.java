package org.taskmanager.user_server.core.converters;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.taskmanager.base_package.dto.PageDTO;
import org.taskmanager.user_client.core.dto.base.UserDTO;
import org.taskmanager.user_server.dao.entity.User;

public class PageToPageDTOConverter implements Converter<Page<User>, PageDTO<UserDTO>> {
    private final ConversionService conversionService;

    public PageToPageDTOConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public PageDTO<UserDTO> convert(Page<User> users) {
        return new PageDTO<>(users.getNumber(), users.getSize(), users.getTotalPages(),
                users.getTotalElements(), users.isFirst(), users.getNumberOfElements(),
                users.isLast(), users.get().map(u -> conversionService.convert(u, UserDTO.class)).toList());
    }
}
