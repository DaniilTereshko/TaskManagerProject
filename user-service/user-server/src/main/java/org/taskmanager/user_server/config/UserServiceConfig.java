package org.taskmanager.user_server.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.client.RestTemplate;
import org.taskmanager.audit_client.client.api.IAuditServiceClient;
import org.taskmanager.user_server.core.converters.ConversionServiceFactory;
import org.taskmanager.user_server.dao.repositories.IUserRepository;
import org.taskmanager.user_server.service.api.IAuditService;
import org.taskmanager.user_server.service.api.IUserService;
import org.taskmanager.user_server.service.services.AuditService;
import org.taskmanager.user_server.service.services.UserService;

@Configuration
public class UserServiceConfig {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public ConversionServiceFactory conversionService(){
        return new ConversionServiceFactory();
    }
    @Bean
    public IAuditService auditService(IAuditServiceClient auditServiceClient){
        return new AuditService(auditServiceClient);
    }
    @Bean
    public IUserService userService(IUserRepository userRepository, IAuditService auditService, ConversionService conversionService){
        return new UserService(userRepository, auditService, conversionService);
    }
}
