package org.taskmanager.report_server.service.implementation.report;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.taskmanager.audit_client.core.dto.base.AuditDTO;
import org.taskmanager.report_server.core.exception.GenerateReportException;
import org.taskmanager.report_server.service.api.report.IReportGenerationService;

import java.io.File;
import java.io.FileOutputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

public class XmlReportGenerationService implements IReportGenerationService {
    public File generateAudit(List<AuditDTO> audit) throws GenerateReportException {
        UUID fileName = UUID.randomUUID();
        File reportFile = new File(fileName + ".xlsx");
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Audit Report");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("UUID");
            headerRow.createCell(1).setCellValue("User");
            headerRow.createCell(2).setCellValue("Mail");
            headerRow.createCell(3).setCellValue("Fio");
            headerRow.createCell(4).setCellValue("Role");
            headerRow.createCell(5).setCellValue("Text");
            headerRow.createCell(6).setCellValue("Type");
            headerRow.createCell(7).setCellValue("ID");
            headerRow.createCell(8).setCellValue("Date Created");

            for (int i = 0; i < audit.size(); i++) {
                AuditDTO auditDTO = audit.get(i);
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(auditDTO.getUuid().toString());
                dataRow.createCell(1).setCellValue(auditDTO.getUser().getUuid().toString());
                dataRow.createCell(2).setCellValue(auditDTO.getUser().getMail());
                dataRow.createCell(3).setCellValue(auditDTO.getUser().getFio());
                dataRow.createCell(4).setCellValue(auditDTO.getUser().getRole().toString());
                dataRow.createCell(5).setCellValue(auditDTO.getText());
                dataRow.createCell(6).setCellValue(auditDTO.getType().toString());
                dataRow.createCell(7).setCellValue(auditDTO.getId());
                dataRow.createCell(8).setCellValue(LocalDateTime.ofInstant(Instant.ofEpochMilli(auditDTO.getCreateDate()), ZoneOffset.UTC).toString());
            }
            try (FileOutputStream fos = new FileOutputStream(reportFile)) {
                workbook.write(fos);
            }
        } catch (Exception e) {
            throw new GenerateReportException();
        }
        return reportFile;
    }
}
