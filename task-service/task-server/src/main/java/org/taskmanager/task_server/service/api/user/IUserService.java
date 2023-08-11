package org.taskmanager.task_server.service.api.user;

import org.taskmanager.task_client.core.dto.base.UserRefDTO;
import org.taskmanager.user_client.core.dto.base.UserDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IUserService {
    UserDTO getMe(String jwt);
    UserDTO get(UserRefDTO user);
    List<UserDTO> get(Set<UserRefDTO> staff);
}
