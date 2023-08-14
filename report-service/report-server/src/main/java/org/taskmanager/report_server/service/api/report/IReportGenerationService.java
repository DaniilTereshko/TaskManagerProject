package org.taskmanager.report_server.service.api.report;

import org.taskmanager.audit_client.core.dto.base.AuditDTO;
import org.taskmanager.report_server.core.exception.GenerateReportException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IReportGenerationService {
    File generateAudit(List<AuditDTO> audit) throws GenerateReportException;
}
