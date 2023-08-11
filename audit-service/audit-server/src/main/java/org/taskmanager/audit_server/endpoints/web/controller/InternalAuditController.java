package org.taskmanager.audit_server.endpoints.web.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taskmanager.audit_server.service.api.IAuditService;
import org.taskmanager.audit_client.core.dto.create.AuditCreateDTO;

@RestController
@RequestMapping("/internal/audit")
public class InternalAuditController {
    private final IAuditService auditService;

    public InternalAuditController(IAuditService auditService, ConversionService conversionService) {
        this.auditService = auditService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody AuditCreateDTO auditCreateDTO){
        this.auditService.save(auditCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
