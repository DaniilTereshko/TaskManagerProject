package org.taskmanager.user_server.service.api;

import org.taskmanager.user_client.core.dto.base.UserDTO;

public interface IAuditService {
    void save(UserDTO item, String action);
}
