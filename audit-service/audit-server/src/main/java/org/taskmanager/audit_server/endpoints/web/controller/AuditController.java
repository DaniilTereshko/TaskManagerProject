package org.taskmanager.audit_server.endpoints.web.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.audit_client.core.dto.base.AuditDTO;
import org.taskmanager.audit_client.core.dto.creare.AuditCreateDTO;
import org.taskmanager.audit_server.core.util.PageToPageDTOUtil;
import org.taskmanager.audit_server.dao.entity.Audit;
import org.taskmanager.audit_server.service.api.IAuditService;
import org.taskmanager.base_package.dto.PageDTO;

import java.util.UUID;

@RestController
public class AuditController {
    private final IAuditService auditService;
    private final ConversionService conversionService;

    public AuditController(IAuditService auditService, ConversionService conversionService) {
        this.auditService = auditService;
        this.conversionService = conversionService;
    }

    @PostMapping(path = "/internal/save", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody AuditCreateDTO auditCreateDTO){
        this.auditService.save(auditCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/audit")
    public ResponseEntity<PageDTO<AuditDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size){
        Page<Audit> audits = this.auditService.get(page, size);
        PageDTO<AuditDTO> pageDTO = PageToPageDTOUtil.convert(audits, conversionService);
        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }
    @GetMapping("/audit/{uuid}")
    public ResponseEntity<AuditDTO> findById(@PathVariable UUID uuid){
        Audit audit = this.auditService.get(uuid);
        AuditDTO dto = conversionService.convert(audit, AuditDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
