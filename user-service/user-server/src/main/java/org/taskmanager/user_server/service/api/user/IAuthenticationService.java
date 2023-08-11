package org.taskmanager.user_server.service.api.user;

import org.taskmanager.user_server.core.dto.base.TokenDTO;
import org.taskmanager.user_server.core.dto.base.UserLoginDTO;
import org.taskmanager.user_server.core.dto.base.UserRegistrationDTO;
import org.taskmanager.user_server.dao.entity.User;

import java.util.UUID;

public interface IAuthenticationService{
    void registration(UserRegistrationDTO item);
    void verification(String email, UUID token);
    TokenDTO login(UserLoginDTO item);
    User getMe();
}
