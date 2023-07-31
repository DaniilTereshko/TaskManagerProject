package org.taskmanager.audit_server.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.taskmanager.audit_client.core.dto.base.AuditDTO;
import org.taskmanager.audit_client.core.dto.base.UserDTO;
import org.taskmanager.audit_server.dao.entity.Audit;

public class AuditToAuditDTOConverter implements Converter<Audit, AuditDTO> {

    @Override
    public AuditDTO convert(Audit audit) {
        return new AuditDTO(audit.getUuid(), new UserDTO(audit.getUserId(), audit.getMail(), audit.getFio(), audit.getRole()),
                audit.getText(), audit.getType(), audit.getId(), audit.getCreateDate());
    }
}
