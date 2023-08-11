package org.taskmanager.report_server.service.implementation;

import org.taskmanager.report_client.core.dto.ReportParamAuditDTO;
import org.taskmanager.report_client.core.enums.ReportType;
import org.taskmanager.report_server.dao.entity.Report;
import org.taskmanager.report_server.dao.repositories.IReportRepository;
import org.taskmanager.report_server.service.api.IReportService;

public class ReportService implements IReportService {
    private final IReportRepository reportRepository;

    public ReportService(IReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report saveAudit(ReportType type, ReportParamAuditDTO param) {
        return null;
    }
}
