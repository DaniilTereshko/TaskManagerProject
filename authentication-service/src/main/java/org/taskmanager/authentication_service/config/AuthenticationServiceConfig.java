package org.taskmanager.authentication_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.taskmanager.authentication_service.service.api.INotificationService;
import org.taskmanager.authentication_service.service.api.IUserService;
import org.taskmanager.authentication_service.service.implemetation.NotificationService;
import org.taskmanager.authentication_service.service.implemetation.UserService;
import org.taskmanager.authentication_service.service.feign_clients.api.INotificationServiceClient;
import org.thymeleaf.TemplateEngine;

@Configuration
public class AuthenticationServiceConfig {
    @Bean
    public INotificationService notificationService(INotificationServiceClient notificationServiceFeignClient, TemplateEngine templateEngine){
        return new NotificationService(notificationServiceFeignClient, templateEngine);
    }
    @Bean
    public IUserService userService(INotificationService notificationService){
        return new UserService(notificationService);
    }
}
