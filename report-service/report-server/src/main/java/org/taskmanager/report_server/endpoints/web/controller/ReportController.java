package org.taskmanager.report_server.endpoints.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.report_client.core.dto.ReportParamAuditDTO;
import org.taskmanager.report_client.core.enums.ReportType;
import org.taskmanager.report_server.service.api.IReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping(path = "/{type}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@PathVariable ReportType type, @RequestBody ReportParamAuditDTO param){
        this.reportService.saveAudit(type, param);

        return new ResponseEntity<>();
    }
}
