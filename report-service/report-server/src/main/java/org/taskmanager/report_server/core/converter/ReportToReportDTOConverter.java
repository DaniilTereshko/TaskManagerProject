package org.taskmanager.report_server.core.converter;


import org.springframework.core.convert.converter.Converter;
import org.taskmanager.report_client.core.dto.ReportDTO;
import org.taskmanager.report_server.dao.entity.Report;

import java.time.ZoneOffset;

public class ReportToReportDTOConverter implements Converter<Report, ReportDTO> {
    @Override
    public ReportDTO convert(Report report) {
        return new ReportDTO(report.getUuid(), report.getStatus(), report.getType(), report.getDescription(),
                report.getParam(), report.getCreateDate().toInstant(ZoneOffset.UTC).toEpochMilli(),
                report.getUpdateDate().toInstant(ZoneOffset.UTC).toEpochMilli());
    }
}
