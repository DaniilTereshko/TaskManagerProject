package org.taskmanager.audit_server.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.taskmanager.audit_client.core.dto.create.AuditCreateDTO;
import org.taskmanager.audit_server.dao.entity.Audit;

public class AuditDTOToAuditConverter implements Converter<AuditCreateDTO, Audit> {
    @Override
    public Audit convert(AuditCreateDTO dto) {
        return new Audit(dto.getText(), dto.getType(), dto.getId(), dto.getUser(), dto.getMail(), dto.getFio(), dto.getRole());
    }
}
