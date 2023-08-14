package org.taskmanager.report_server.endpoints.web.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.base_package.dto.PageDTO;
import org.taskmanager.report_client.core.dto.FileDTO;
import org.taskmanager.report_client.core.dto.ReportDTO;
import org.taskmanager.report_client.core.enums.ReportType;
import org.taskmanager.report_server.dao.entity.Report;
import org.taskmanager.report_server.service.api.report.IReportService;
import org.taskmanager.report_server.service.util.PageConverter;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final IReportService reportService;
    private final PageConverter pageConverter;

    public ReportController(IReportService reportService, PageConverter pageConverter) {
        this.reportService = reportService;
        this.pageConverter = pageConverter;
    }

    @PostMapping(path = "/{type}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@PathVariable ReportType type, @RequestBody(required = false) Map<String, Object> param){
        this.reportService.save(type, param);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<PageDTO<ReportDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size){
        Page<Report> reports = this.reportService.getAll(size, page);
        PageDTO<ReportDTO> dto = pageConverter.convertPageToDTO(reports, ReportDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{uuid}/export")
    public ResponseEntity<ByteArrayResource> export(@PathVariable UUID uuid){
        FileDTO export = this.reportService.export(uuid);
        ByteArrayResource resource = new ByteArrayResource(export.getContent());
        return ResponseEntity
                .ok()
                .contentLength(export.getContent().length)
                .header("Content-type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .header("Content-disposition", "attachment; filename=\"" + export.getFileName() + "\"")
                .body(resource);
    }
    @RequestMapping(value = "/{uuid}/export", method = RequestMethod.HEAD)
    public ResponseEntity<?> reportIsReady(@PathVariable UUID uuid){
        this.reportService.isReady(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
