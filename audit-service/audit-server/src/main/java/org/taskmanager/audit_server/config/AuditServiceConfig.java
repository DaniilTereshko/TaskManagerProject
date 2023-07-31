package org.taskmanager.audit_server.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.client.RestTemplate;
import org.taskmanager.audit_server.core.converters.ConversionServiceFactory;
import org.taskmanager.audit_server.dao.repositories.IAuditRepository;
import org.taskmanager.audit_server.service.api.IAuditService;
import org.taskmanager.audit_server.service.implementation.AuditService;

@Configuration
public class AuditServiceConfig {

    @Bean
    public ConversionServiceFactory conversionService(){
        return new ConversionServiceFactory();
    }
    @Bean
    public IAuditService auditService(IAuditRepository auditRepository, ConversionService conversionService){
        return new AuditService(auditRepository, conversionService);
    }
}
