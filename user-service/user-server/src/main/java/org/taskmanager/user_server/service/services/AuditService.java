package org.taskmanager.user_server.service.services;

import org.taskmanager.audit_client.client.api.IAuditServiceClient;
import org.taskmanager.audit_client.core.dto.creare.AuditCreateDTO;
import org.taskmanager.audit_client.core.enums.EssenceType;
import org.taskmanager.user_client.core.dto.base.UserDTO;
import org.taskmanager.user_server.service.api.IAuditService;

public class AuditService implements IAuditService {
    private final IAuditServiceClient auditServiceClient;

    public AuditService(IAuditServiceClient auditServiceClient) {
        this.auditServiceClient = auditServiceClient;
    }

    @Override
    public void save(UserDTO item, String action) {
        AuditCreateDTO dto = new AuditCreateDTO(item.getUuid(), item.getMail(), item.getFio(), item.getRole(),
                action, EssenceType.USER, item.getUuid().toString());
        this.auditServiceClient.save(dto);
    }
}
