package org.taskmanager.report_server.service.api.report;

import org.springframework.data.domain.Page;
import org.taskmanager.base_package.dto.PageDTO;
import org.taskmanager.report_client.core.dto.FileDTO;
import org.taskmanager.report_client.core.dto.ReportParamAuditDTO;
import org.taskmanager.report_client.core.enums.ReportType;
import org.taskmanager.report_server.dao.entity.Report;

import java.io.File;
import java.util.Map;
import java.util.UUID;

public interface IReportService {
    void save(ReportType type, Map<String, Object> param);
    Page<Report> getAll(int size, int page);
    FileDTO export(UUID uuid);
    void isReady(UUID uuid);
}
