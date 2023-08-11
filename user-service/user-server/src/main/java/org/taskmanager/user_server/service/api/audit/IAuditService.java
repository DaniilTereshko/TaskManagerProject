package org.taskmanager.user_server.service.api.audit;

import org.taskmanager.user_client.core.dto.base.UserDTO;
import org.taskmanager.user_server.dao.entity.User;

public interface IAuditService {
    void save(User editor, String action, String id);
    void save(String action, String id);
}
