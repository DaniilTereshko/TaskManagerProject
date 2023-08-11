package org.taskmanager.audit_server.service.implementation;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.taskmanager.audit_server.core.exception.IncorrectDataException;
import org.taskmanager.audit_server.core.exception.NotFoundException;
import org.taskmanager.audit_server.dao.entity.Audit;
import org.taskmanager.audit_server.dao.repositories.IAuditRepository;
import org.taskmanager.audit_server.service.api.IAuditService;
import org.taskmanager.audit_client.core.dto.create.AuditCreateDTO;

import java.util.UUID;

public class AuditService implements IAuditService {
    private static final String INCORRECT_DATA = "Неверные данные. Попробуйте еще раз";
    private static final String AUDIT_NOT_FOUND = "Аудит не найден";
    private final IAuditRepository auditRepository;
    private final ConversionService conversionService;

    public AuditService(IAuditRepository auditRepository, ConversionService conversionService) {
        this.auditRepository = auditRepository;
        this.conversionService = conversionService;
    }
    @Transactional
    @Override
    public Audit save(AuditCreateDTO item) {
        Audit audit = this.conversionService.convert(item, Audit.class);
        audit.setUuid(UUID.randomUUID());
        return this.auditRepository.save(audit);
    }
    @Transactional(readOnly = true)
    @Override
    public Page<Audit> get(int page, int size) {
        if(page < 0 && size < 0){
            throw new IncorrectDataException(INCORRECT_DATA);
        }
        PageRequest request = PageRequest.of(page, size);
        Page<Audit> audits = this.auditRepository.findAll(request);
        return audits;
    }
    @Transactional(readOnly = true)
    @Override
    public Audit get(UUID uuid) {
        return this.auditRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(AUDIT_NOT_FOUND));
    }
}
