package org.taskmanager.user_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.taskmanager.user_service.core.converters.UserDTOToUserConverter;
import org.taskmanager.user_service.core.converters.UserToUserDTOConverter;

@Configuration
public class WebUserServiceConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserDTOToUserConverter());
        registry.addConverter(new UserToUserDTOConverter());
    }
}
