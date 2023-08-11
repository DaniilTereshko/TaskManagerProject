package org.taskmanager.task_server.service.implemetation.user;

import org.taskmanager.task_client.core.dto.base.UserRefDTO;
import org.taskmanager.task_server.endpoints.web.util.JwtHandler;
import org.taskmanager.task_server.service.api.user.IUserService;
import org.taskmanager.user_client.client.api.IUserServiceClient;
import org.taskmanager.user_client.core.dto.base.UserDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UserService implements IUserService {
    private final IUserServiceClient userServiceClient;
    private final JwtHandler jwtHandler;

    public UserService(IUserServiceClient userServiceClient, JwtHandler jwtHandler) {
        this.userServiceClient = userServiceClient;
        this.jwtHandler = jwtHandler;
    }

    @Override
    public UserDTO getMe(String jwt) {
        return this.userServiceClient.getMe("Bearer " + jwt).getBody();
    }

    @Override
    public UserDTO get(UserRefDTO user) {
        String systemToken = this.jwtHandler.generateSystemAccessToken("task-service");
        return this.userServiceClient.get("Bearer " + systemToken, user.getUuid()).getBody();
    }
    public List<UserDTO> get(Set<UserRefDTO> staff){
        String systemToken = this.jwtHandler.generateSystemAccessToken("task-service");
        List<UUID> uuids = staff.stream().map(UserRefDTO::getUuid).toList();
        return this.userServiceClient.get("Bearer " + systemToken, uuids).getBody();
    }
}
