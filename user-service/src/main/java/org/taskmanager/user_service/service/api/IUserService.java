package org.taskmanager.user_service.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.taskmanager.user_service.core.dto.create.UserCreateDTO;
import org.taskmanager.user_service.core.dto.update.UserUpdateDTO;
import org.taskmanager.user_service.dao.entity.User;

import java.util.UUID;

public interface IUserService {
    User save(UserCreateDTO item);
    Page<User> get(PageRequest pageRequest);
    User get(UUID uuid);
    User update(UserUpdateDTO item);
}
