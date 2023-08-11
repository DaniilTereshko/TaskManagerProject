package org.taskmanager.audit_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.taskmanager.audit_server.core.converters.ConversionServiceFactory;
import org.taskmanager.audit_server.dao.repositories.IAuditRepository;
import org.taskmanager.audit_server.endpoints.web.util.JwtHandler;
import org.taskmanager.audit_server.service.api.IAuditService;
import org.taskmanager.audit_server.service.api.IUserService;
import org.taskmanager.audit_server.service.implementation.AuditService;
import org.taskmanager.audit_server.service.implementation.UserService;
import org.taskmanager.user_client.client.api.IUserServiceClient;

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
    @Bean
    public IUserService userService(IUserServiceClient userServiceClient){
        return new UserService(userServiceClient);
    }
}
