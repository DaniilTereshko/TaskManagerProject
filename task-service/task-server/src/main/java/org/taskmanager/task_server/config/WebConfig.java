package org.taskmanager.task_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.taskmanager.task_server.core.converters.StringToProjectRefDTOConverter;
import org.taskmanager.task_server.core.converters.StringToUserRefDTOConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToProjectRefDTOConverter());
        registry.addConverter(new StringToUserRefDTOConverter());
    }
}
