package org.taskmanager.user_service.core.converters;

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
            registry.addConverter(new UserPageToUserPageDTOConverter(conversionService));
            registry.addConverter(new GenericUserDTOToUserConverter());
            registry.addConverter(new UserToUserDTOConverter());
        }
    }
}
