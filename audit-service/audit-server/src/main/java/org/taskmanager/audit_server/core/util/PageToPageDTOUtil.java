package org.taskmanager.audit_server.core.util;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.taskmanager.audit_client.core.dto.base.AuditDTO;
import org.taskmanager.audit_server.dao.entity.Audit;
import org.taskmanager.base_package.dto.PageDTO;

public class PageToPageDTOUtil {

    private PageToPageDTOUtil() {
    }

    public static PageDTO<AuditDTO> convert(Page<Audit> users, ConversionService conversionService){
        return new PageDTO<>(users.getNumber(), users.getSize(), users.getTotalPages(),
                users.getTotalElements(), users.isFirst(), users.getNumberOfElements(),
                users.isLast(), users.get().map(u -> conversionService.convert(u, AuditDTO.class)).toList());
    }
}
