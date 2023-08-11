package org.taskmanager.user_server.service.api.user;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.taskmanager.user_client.core.dto.create.UserCreateDTO;
import org.taskmanager.user_client.core.dto.update.UserUpdateDTO;
import org.taskmanager.user_server.core.dto.base.UserRegistrationDTO;
import org.taskmanager.user_server.dao.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    User save(UserCreateDTO item);
    User save(UserRegistrationDTO item);
    Page<User> get(int page, int size);
    User get(UUID uuid);
    User update(UserUpdateDTO item);
    Optional<User> findByEmail(String email);
    List<User> get(List<UUID> users);
    void activate(String email);
}
