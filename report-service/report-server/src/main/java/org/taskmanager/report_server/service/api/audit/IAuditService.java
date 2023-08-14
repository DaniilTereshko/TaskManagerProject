package org.taskmanager.report_server.service.api.audit;

import org.taskmanager.audit_client.core.dto.base.AuditDTO;
import org.taskmanager.report_client.core.dto.ReportParamAuditDTO;

import java.util.List;

public interface IAuditService {

    List<AuditDTO> getAllByParam(ReportParamAuditDTO param);
}
