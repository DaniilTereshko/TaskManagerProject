package org.taskmanager.report_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.taskmanager.report_server.core.converter.ParamToReportParamAuditDTOConverter;
import org.taskmanager.report_server.core.converter.ReportToReportDTOConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ParamToReportParamAuditDTOConverter());
        registry.addConverter(new ReportToReportDTOConverter());
    }
}
