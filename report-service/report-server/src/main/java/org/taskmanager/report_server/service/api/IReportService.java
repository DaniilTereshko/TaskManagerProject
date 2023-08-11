package org.taskmanager.report_server.service.api;

import org.taskmanager.report_client.core.dto.ReportParamAuditDTO;
import org.taskmanager.report_client.core.enums.ReportType;
import org.taskmanager.report_server.dao.entity.Report;

public interface IReportService {
    Report saveAudit(ReportType type, ReportParamAuditDTO param);
}
