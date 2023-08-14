package org.taskmanager.report_server.core.converter;

import org.springframework.core.convert.converter.Converter;
import org.taskmanager.report_client.core.dto.ReportParamAuditDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

public class ParamToReportParamAuditDTOConverter implements Converter<Map<String, String>, ReportParamAuditDTO> {
    @Override
    public ReportParamAuditDTO convert(Map<String, String> param) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String user = param.get("user");
        UUID userUUID = user != null ? UUID.fromString(user) : null;
        String from = param.get("from");
        LocalDate fromDate = from != null ? LocalDate.parse(from, formatter) : null;
        String to = param.get("to");
        LocalDate toDate = to != null ? LocalDate.parse(to, formatter) : null;
        return new ReportParamAuditDTO(userUUID, fromDate, toDate);
    }
}
