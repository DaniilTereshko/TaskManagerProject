package org.taskmanager.authentication_service.service.api;

import org.taskmanager.authentication_service.core.dto.base.UserRegistrationDTO;

public interface IUserService {
    void registration(UserRegistrationDTO item);
    boolean activate(String email, String code);
}
