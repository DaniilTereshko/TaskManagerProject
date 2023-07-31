package org.taskmanager.audit_client.client.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.taskmanager.audit_client.core.dto.creare.AuditCreateDTO;

@FeignClient(name = "audit-service")
public interface IAuditServiceClient{
    @PostMapping(value = "/internal/save", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> save(@RequestBody AuditCreateDTO auditCreateDTO);
}
