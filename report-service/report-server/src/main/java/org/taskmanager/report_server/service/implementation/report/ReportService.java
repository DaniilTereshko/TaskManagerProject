package org.taskmanager.report_server.service.implementation.report;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.taskmanager.audit_client.core.dto.base.AuditDTO;
import org.taskmanager.report_client.core.dto.FileDTO;
import org.taskmanager.report_client.core.dto.ReportParamAuditDTO;
import org.taskmanager.report_client.core.enums.ReportStatus;
import org.taskmanager.report_client.core.enums.ReportType;
import org.taskmanager.report_server.core.exception.NotFoundException;
import org.taskmanager.report_server.core.exception.ReportNotReadyException;
import org.taskmanager.report_server.dao.entity.Report;
import org.taskmanager.report_server.dao.repositories.IReportRepository;
import org.taskmanager.report_server.service.api.report.IAuditReportService;
import org.taskmanager.report_server.service.api.audit.IAuditService;
import org.taskmanager.report_server.service.api.report.IReportService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ReportService implements IReportService {
    private static final String NOT_AVAILABLE_DATA = "Нет доступных данных для формирования отчета";
    private static final String REPORT_NOT_FOUND = "Отчет не найден";
    private static final String REPORT_NOT_READY = "Отчет не готов";
    private final IReportRepository reportRepository;
    private final IAuditService auditService;
    private final MinioAdapter minioAdapter;
    private final IAuditReportService auditReportService;
    private final ConversionService conversionService;

    public ReportService(IReportRepository reportRepository, IAuditService auditService, MinioAdapter minioAdapter, IAuditReportService auditReportService, ConversionService conversionService) {
        this.reportRepository = reportRepository;
        this.auditService = auditService;
        this.minioAdapter = minioAdapter;
        this.auditReportService = auditReportService;
        this.conversionService = conversionService;
    }

    @Override
    public void save(ReportType type, Map<String, Object> param) {
        if(type.equals(ReportType.JOURNAL_AUDIT)){
            this.auditReportService.validateAuditParam(param);
            ReportParamAuditDTO paramAudit = this.conversionService.convert(param, ReportParamAuditDTO.class);
            List<AuditDTO> auditList = this.auditService.getAllByParam(paramAudit);
            if (auditList.isEmpty()) {
                throw new NotFoundException(NOT_AVAILABLE_DATA);
            }
            Report report = this.auditReportService.generateAndUploadAuditReport(auditList, param);
            this.reportRepository.save(report);
        }
    }

    @Override
    public Page<Report> getAll(int size, int page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.reportRepository.findAll(pageRequest);
    }

    @Override
    public FileDTO export(UUID uuid) {
        Report report = this.reportRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(REPORT_NOT_FOUND));
        String fileName = report.getFile();
        return this.minioAdapter.download(fileName);
    }

    @Override
    public void isReady(UUID uuid) {
        Report report = this.reportRepository.findById(uuid)
                .orElseThrow(() -> new ReportNotReadyException(REPORT_NOT_FOUND));
        if (!report.getStatus().equals(ReportStatus.DONE)){
            throw new ReportNotReadyException(REPORT_NOT_READY);
        }
    }
}
