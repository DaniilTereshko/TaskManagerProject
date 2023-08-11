package org.taskmanager.task_server.service.api.audit;

import org.taskmanager.audit_client.core.enums.EssenceType;
import org.taskmanager.user_client.core.dto.base.UserDTO;

public interface IAuditService {
    void save(UserDTO editor, String action, String id, EssenceType type);
    void save(String action, String id, EssenceType type);
}
