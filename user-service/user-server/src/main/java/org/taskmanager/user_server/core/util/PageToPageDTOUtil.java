package org.taskmanager.user_server.core.util;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.taskmanager.base_package.dto.PageDTO;
import org.taskmanager.user_client.core.dto.base.UserDTO;
import org.taskmanager.user_server.dao.entity.User;

public class PageToPageDTOUtil {//TODO ???

    private PageToPageDTOUtil() {
    }

    public static PageDTO<UserDTO> convert(Page<User> users, ConversionService conversionService){
        return new PageDTO<>(users.getNumber(), users.getSize(), users.getTotalPages(),
                users.getTotalElements(), users.isFirst(), users.getNumberOfElements(),
                users.isLast(), users.get().map(u -> conversionService.convert(u, UserDTO.class)).toList());
    }
}
