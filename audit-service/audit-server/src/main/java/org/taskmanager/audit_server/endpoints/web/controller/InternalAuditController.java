package org.taskmanager.audit_server.endpoints.web.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.audit_server.dao.entity.Audit;
import org.taskmanager.audit_server.service.api.IAuditService;
import org.taskmanager.audit_client.core.dto.create.AuditCreateDTO;
import org.taskmanager.report_client.core.dto.ReportParamAuditDTO;
import org.taskmanager.audit_client.core.dto.base.AuditDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/internal/audit")
public class InternalAuditController {
    private final IAuditService auditService;
    private final ConversionService conversionService;

    public InternalAuditController(IAuditService auditService, ConversionService conversionService) {
        this.auditService = auditService;
        this.conversionService = conversionService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody AuditCreateDTO auditCreateDTO){
        this.auditService.save(auditCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/find")
    public ResponseEntity<List<AuditDTO>> getByParam(
            @RequestParam(required = false) UUID uuid,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate){
        ReportParamAuditDTO param = new ReportParamAuditDTO(uuid, startDate, endDate);
        List<Audit> list = this.auditService.getAllByParam(param);
        List<AuditDTO> dtos = list.stream().map(a -> this.conversionService.convert(a, AuditDTO.class)).toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
