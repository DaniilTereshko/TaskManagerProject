package org.taskmanager.report_server.service.implementation.user;

import org.taskmanager.report_server.service.api.user.IUserService;
import org.taskmanager.user_client.client.api.IUserServiceClient;
import org.taskmanager.user_client.core.dto.base.UserDTO;

public class UserService implements IUserService {
    private final IUserServiceClient userServiceClient;

    public UserService(IUserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public UserDTO getMe(String jwt) {
        UserDTO user = this.userServiceClient.getMe("Bearer " + jwt).getBody();
        return user;
    }
}
