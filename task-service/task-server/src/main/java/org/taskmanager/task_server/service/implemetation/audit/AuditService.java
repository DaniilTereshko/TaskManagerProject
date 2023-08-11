package org.taskmanager.task_server.service.implemetation.audit;

import org.taskmanager.audit_client.client.api.IAuditServiceClient;
import org.taskmanager.audit_client.core.dto.create.AuditCreateDTO;
import org.taskmanager.audit_client.core.enums.EssenceType;
import org.taskmanager.task_server.endpoints.web.util.JwtHandler;
import org.taskmanager.task_server.service.api.audit.IAuditService;
import org.taskmanager.task_server.service.implemetation.user.UserHolder;
import org.taskmanager.user_client.core.dto.base.UserDTO;

public class AuditService implements IAuditService {
    private final IAuditServiceClient auditServiceClient;
    private final JwtHandler jwtHandler;
    private final UserHolder userHolder;

    public AuditService(IAuditServiceClient auditServiceClient, JwtHandler jwtHandler, UserHolder userHolder) {
        this.auditServiceClient = auditServiceClient;
        this.jwtHandler = jwtHandler;
        this.userHolder = userHolder;
    }
    @Override
    public void save(UserDTO editor, String action, String id, EssenceType type) {
        String jwt = jwtHandler.generateSystemAccessToken("user-service");
        AuditCreateDTO dto = new AuditCreateDTO(editor.getUuid(), editor.getMail(), editor.getFio(), editor.getRole(),
                action, type, id);
        this.auditServiceClient.save("Bearer " + jwt, dto);
    }

    @Override
    public void save(String action, String id, EssenceType type) {
        String jwt = jwtHandler.generateSystemAccessToken("user-service");
        UserDTO editor = this.userHolder.getUser();
        AuditCreateDTO dto = new AuditCreateDTO(editor.getUuid(), editor.getMail(), editor.getFio(), editor.getRole(),
                action, type, id);
        this.auditServiceClient.save("Bearer " + jwt, dto);
    }
}
