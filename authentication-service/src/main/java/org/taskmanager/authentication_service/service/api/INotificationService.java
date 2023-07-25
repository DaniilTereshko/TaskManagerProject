package org.taskmanager.authentication_service.service.api;

import org.taskmanager.authentication_service.core.dto.base.UserDTO;

import java.util.UUID;

public interface INotificationService {
    UUID sendActivateMail(UserDTO userDTO);
}
