package org.taskmanager.user_service.service.api;

import org.springframework.data.domain.PageRequest;
import org.taskmanager.user_service.core.dto.base.UserPageDTO;
import org.taskmanager.user_service.core.dto.base.UserRegistrationDTO;
import org.taskmanager.user_service.core.dto.create.UserCreateDTO;
import org.taskmanager.user_service.core.dto.update.UserUpdateDTO;
import org.taskmanager.user_service.dao.entity.User;

import java.util.UUID;

public interface IUserService {
    User save(UserCreateDTO item);
    UserPageDTO get(PageRequest pageRequest);
    User get(UUID uuid);
    User update(UserUpdateDTO item);
    void registration(UserRegistrationDTO item);
    boolean activate(String email, String code);
}
