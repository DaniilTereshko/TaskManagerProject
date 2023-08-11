package org.taskmanager.audit_server.service.api;

import org.taskmanager.user_client.core.dto.base.UserDTO;

public interface IUserService {
    UserDTO getMe(String jwt);
}
