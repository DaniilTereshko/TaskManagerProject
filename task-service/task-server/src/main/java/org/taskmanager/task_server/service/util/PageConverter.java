package org.taskmanager.task_server.service.util;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.taskmanager.base_package.dto.PageDTO;

@Component
public class PageConverter {
    private final ConversionService conversionService;

    public PageConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
    public <C, DTO> PageDTO<DTO> convertPageToDTO(Page<C> page, Class<DTO> dtoClass) {
        return new PageDTO<>(page.getNumber(), page.getSize(), page.getTotalPages(),
                page.getTotalElements(), page.isFirst(), page.getNumberOfElements(),
                page.isLast(), page.get().map(u -> conversionService.convert(u, dtoClass)).toList());

    }
}
