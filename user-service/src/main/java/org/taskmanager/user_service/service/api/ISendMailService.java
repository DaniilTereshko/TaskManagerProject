package org.taskmanager.user_service.service.api;

import org.taskmanager.user_service.core.dto.base.UserDTO;

import java.util.UUID;

public interface ISendMailService {//TODO норм или не норм?
    UUID sendActivateMail(UserDTO userDTO);
}
