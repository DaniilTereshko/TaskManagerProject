package org.taskmanager.user_server.service.services.audit;

import org.taskmanager.audit_client.client.api.IAuditServiceClient;
import org.taskmanager.audit_client.core.dto.create.AuditCreateDTO;
import org.taskmanager.audit_client.core.enums.EssenceType;
import org.taskmanager.user_client.core.dto.base.UserDetailsDTO;
import org.taskmanager.user_client.core.enums.UserRole;
import org.taskmanager.user_server.dao.entity.User;
import org.taskmanager.user_server.endpoints.web.util.JwtHandler;
import org.taskmanager.user_server.service.api.audit.IAuditService;
import org.taskmanager.user_server.service.services.user.UserHolder;

import java.util.List;

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
    public void save(User editor, String action, String id) {
        String jwt = jwtHandler.generateSystemAccessToken("user-service");
        AuditCreateDTO dto = new AuditCreateDTO(editor.getUuid(), editor.getEmail(), editor.getFio(), editor.getRole(),
                action, EssenceType.USER, id);
        this.auditServiceClient.save("Bearer " + jwt, dto);
    }

    @Override
    public void save(String action, String id) {
        String jwt = jwtHandler.generateSystemAccessToken("user-service");
        UserDetailsDTO editor = (UserDetailsDTO) this.userHolder.getUser();
        List<UserRole> authorities = (List<UserRole>) editor.getAuthorities();
        AuditCreateDTO dto = new AuditCreateDTO(editor.getUuid(), editor.getUsername(), editor.getFio(), authorities.get(0),
                action, EssenceType.USER, id);
        this.auditServiceClient.save("Bearer " + jwt, dto);
    }
}
