package org.taskmanager.audit_client.client.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.audit_client.core.dto.base.AuditDTO;
import org.taskmanager.audit_client.core.dto.create.AuditCreateDTO;
import org.taskmanager.report_client.core.dto.ReportParamAuditDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@FeignClient(name = "audit-service")
public interface IAuditServiceClient{
    @PostMapping(value = "/internal/audit", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> save(@RequestHeader("Authorization") String jwt,
                           @RequestBody AuditCreateDTO auditCreateDTO);
    @GetMapping("/internal/audit/find")
    ResponseEntity<List<AuditDTO>> getByParam(@RequestHeader("Authorization") String jwt,
                                              @RequestParam UUID uuid,
                                              @RequestParam LocalDate startDate,
                                              @RequestParam LocalDate endDate);
}
