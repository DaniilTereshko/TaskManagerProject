package org.taskmanager.user_server.core.converters;

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
            registry.addConverter(new GenericUserDTOToUserConverter());
            registry.addConverter(new GenericUserToUserDTOConverter());
            registry.addConverter(new UserToUserDetailsDTOConverter());
            registry.addConverter(new PageToPageDTOConverter(conversionService));
        }
    }
}
