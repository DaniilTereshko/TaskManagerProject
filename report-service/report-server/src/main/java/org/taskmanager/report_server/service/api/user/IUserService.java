package org.taskmanager.report_server.service.api.user;

import org.taskmanager.user_client.core.dto.base.UserDTO;

public interface IUserService {
    UserDTO getMe(String jwt);
}
