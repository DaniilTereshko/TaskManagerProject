package org.taskmanager.report_server.service.implementation.audit;

import org.taskmanager.audit_client.client.api.IAuditServiceClient;
import org.taskmanager.audit_client.core.dto.base.AuditDTO;
import org.taskmanager.report_client.core.dto.ReportParamAuditDTO;
import org.taskmanager.report_server.endpoints.web.util.JwtHandler;
import org.taskmanager.report_server.service.api.audit.IAuditService;

import java.util.List;

public class AuditService implements IAuditService {
    private final IAuditServiceClient auditServiceClient;
    private final JwtHandler jwtHandler;

    public AuditService(IAuditServiceClient auditServiceClient, JwtHandler jwtHandler) {
        this.auditServiceClient = auditServiceClient;
        this.jwtHandler = jwtHandler;
    }

    @Override
    public List<AuditDTO> getAllByParam(ReportParamAuditDTO param) {
        String jwt = this.jwtHandler.generateSystemAccessToken("report-service");
        if(param == null){
            param = new ReportParamAuditDTO();
        }
        return this.auditServiceClient.getByParam("Bearer " + jwt, param.getUser(),
                param.getFrom(), param.getTo()).getBody();
    }
}
