package org.taskmanager.audit_server.service.implementation;

import org.taskmanager.audit_server.service.api.IUserService;
import org.taskmanager.user_client.client.api.IUserServiceClient;
import org.taskmanager.user_client.core.dto.base.UserDTO;
import org.taskmanager.user_client.core.dto.base.UserDetailsDTO;

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
