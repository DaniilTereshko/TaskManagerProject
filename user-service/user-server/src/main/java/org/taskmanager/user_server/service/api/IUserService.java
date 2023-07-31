package org.taskmanager.user_server.service.api;

import org.springframework.data.domain.Page;
import org.taskmanager.user_client.core.dto.create.UserCreateDTO;
import org.taskmanager.user_client.core.dto.update.UserUpdateDTO;
import org.taskmanager.user_server.dao.entity.User;

import java.util.UUID;

public interface IUserService {
    User save(UserCreateDTO item);
    Page<User> get(int page, int size);
    User get(UUID uuid);
    User update(UserUpdateDTO item);
}
