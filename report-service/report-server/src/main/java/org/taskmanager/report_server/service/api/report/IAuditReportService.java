package org.taskmanager.report_server.service.api.report;

import org.taskmanager.audit_client.core.dto.base.AuditDTO;
import org.taskmanager.report_server.dao.entity.Report;

import java.util.List;
import java.util.Map;

public interface IAuditReportService {
    Report generateAndUploadAuditReport(List<AuditDTO> audit, Map<String, Object> param);
    String generateAuditDescription(Map<String, Object> param);
    void validateAuditParam(Map<String, Object> param);
}
