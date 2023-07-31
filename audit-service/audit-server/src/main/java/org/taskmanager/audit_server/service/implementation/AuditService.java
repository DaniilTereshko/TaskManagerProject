package org.taskmanager.audit_server.service.implementation;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.taskmanager.audit_client.core.dto.creare.AuditCreateDTO;
import org.taskmanager.audit_server.dao.entity.Audit;
import org.taskmanager.audit_server.dao.repositories.IAuditRepository;
import org.taskmanager.audit_server.endpoints.web.exception_hendler.exception.NoSuchAuditException;
import org.taskmanager.audit_server.service.api.IAuditService;

import java.util.UUID;

public class AuditService implements IAuditService {
    private final IAuditRepository auditRepository;
    private final ConversionService conversionService;

    public AuditService(IAuditRepository auditRepository, ConversionService conversionService) {
        this.auditRepository = auditRepository;
        this.conversionService = conversionService;
    }

    @Override
    public Audit save(AuditCreateDTO item) {
        Audit audit = this.conversionService.convert(item, Audit.class);
        audit.setUuid(UUID.randomUUID());
        return auditRepository.save(audit);
    }

    @Override
    public Page<Audit> get(int page, int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<Audit> audits = auditRepository.findAll(request);
        return audits;
    }

    @Override
    public Audit get(UUID uuid) {
        return this.auditRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchAuditException(uuid));
    }
}
