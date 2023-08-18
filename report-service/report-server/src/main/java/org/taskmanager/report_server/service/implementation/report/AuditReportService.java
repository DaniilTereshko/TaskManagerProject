package org.taskmanager.report_server.service.implementation.report;

import org.springframework.core.convert.ConversionService;
import org.taskmanager.audit_client.core.dto.base.AuditDTO;
import org.taskmanager.report_client.core.dto.ReportParamAuditDTO;
import org.taskmanager.report_client.core.enums.ReportStatus;
import org.taskmanager.report_client.core.enums.ReportType;
import org.taskmanager.report_server.core.exception.GenerateReportException;
import org.taskmanager.report_server.core.exception.InvalidAuditParamsException;
import org.taskmanager.report_server.core.exception.ReportUploadException;
import org.taskmanager.report_server.dao.entity.Report;
import org.taskmanager.report_server.service.api.report.IAuditReportService;
import org.taskmanager.report_server.service.api.report.IReportGenerationService;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

public class AuditReportService implements IAuditReportService {
    private static final String UNAVAILABLE_PARAMS = "Недопустимые параметры для отчета по аудиту";
    private static final String INVALID_PARAMS_TYPE = "Недопустимый тип параметров";
    private static final String INVALID_UUID = "Некорректный пользователь";
    private static final String INVALID_DATE = "Некорректная дата";
    private static final String INVALID_PERIOD = "Некорректный временной промежуток";
    private static final String GENERATION_REPORT_ERROR = "Ошибка при генерации отчета";
    private final IReportGenerationService reportGenerationService;
    private final MinioAdapter minioAdapter;
    private final ConversionService conversionService;

    public AuditReportService(IReportGenerationService reportGenerationService, MinioAdapter minioAdapter, ConversionService conversionService) {
        this.reportGenerationService = reportGenerationService;
        this.minioAdapter = minioAdapter;
        this.conversionService = conversionService;
    }
    @Override
    public Report generateAndUploadAuditReport(List<AuditDTO> audit, Map<String, Object> param){
        try {
            File reportFile = this.reportGenerationService.generateAudit(audit);
            String fileName = this.minioAdapter.upload(reportFile);
            return new Report(UUID.randomUUID(), ReportStatus.DONE, ReportType.JOURNAL_AUDIT,
                    this.generateAuditDescription(param),
                    fileName, param);
        } catch (GenerateReportException | ReportUploadException e) {
            return new Report(UUID.randomUUID(), ReportStatus.ERROR, ReportType.JOURNAL_AUDIT,
                    GENERATION_REPORT_ERROR,
                    null, param);
        }
    }
    @Override
    public String generateAuditDescription(Map<String, Object> param){
        String description = "Журнал аудита";
        if(param == null){
            return description;
        }
        ReportParamAuditDTO paramAudit = this.conversionService.convert(param, ReportParamAuditDTO.class);
        UUID user = paramAudit.getUser();
        LocalDate from = paramAudit.getFrom();
        LocalDate to = paramAudit.getTo();

        LocalDate currentDate = LocalDate.now();

        StringBuilder result = new StringBuilder(description);

        if (from != null || to != null) {
            if (from != null) {
                result.append(" за:");
                result.append(" ").append(from.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            }
            if (to != null) {
                if (from != null) {
                    result.append(" - ").append(to.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                } else {
                    result.append(" до ").append(to.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                }
            }
        }
        if (to == null && from != null) {
            result.append(" - ").append(currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        }
        if (user != null) {
            result.append(" По пользователю: [").append(user).append("]");
        }
        return result.toString();
    }
    @Override
    public void validateAuditParam(Map<String, Object> param) {
        if(param == null){
            return;
        }
        boolean isAvailableParams = Stream.of("user", "from", "to")
                .anyMatch(param::containsKey);
        if (!isAvailableParams) {
            throw new InvalidAuditParamsException(UNAVAILABLE_PARAMS);
        }
        for (Object value : param.values()) {
            if (!(value instanceof String)) {
                throw new InvalidAuditParamsException(INVALID_PARAMS_TYPE);
            }
        }
        if (param.get("user") != null){
            try {
                UUID user = UUID.fromString((String) param.get("user"));
            } catch (IllegalArgumentException e) {
                throw new InvalidAuditParamsException(INVALID_UUID, "user");
            }
        }
        LocalDate from = null;
        LocalDate to = null;
        if(param.get("to") != null) {
            try {
                to = LocalDate.parse((String) param.get("to"));
            } catch (Exception e) {
                throw new InvalidAuditParamsException(INVALID_DATE, "to");
            }
        }
        if(param.get("from") != null) {
            try {
                from = LocalDate.parse((String) param.get("from"));
            } catch (Exception e) {
                throw new InvalidAuditParamsException(INVALID_DATE, "from");
            }
        }
        if(from != null && to != null) {
            if (!(from.isBefore(to) || from.isEqual(to))) {
                throw new InvalidAuditParamsException(INVALID_PERIOD);
            }
        }
    }
}
