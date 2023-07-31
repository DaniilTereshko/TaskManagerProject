package org.taskmanager.audit_server.core.converters;

import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;

public class ConversionServiceFactory extends ConversionServiceFactoryBean {
    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        ConversionService conversionService = getObject();
        if(conversionService != null) {
            ConverterRegistry registry = (ConverterRegistry) conversionService;
            registry.addConverter(new AuditDTOToAuditConverter());
            registry.addConverter(new AuditToAuditDTOConverter());
        }
    }
}
