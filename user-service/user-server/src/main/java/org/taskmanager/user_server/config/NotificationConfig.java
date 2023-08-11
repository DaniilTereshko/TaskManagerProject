package org.taskmanager.user_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.taskmanager.audit_client.client.api.INotificationServiceClient;
import org.taskmanager.user_server.config.properties.NotificationProperty;
import org.taskmanager.user_server.dao.repositories.IVerificationTokenRepository;
import org.taskmanager.user_server.endpoints.web.util.JwtHandler;
import org.taskmanager.user_server.service.api.notification.IVerificationTokenService;
import org.taskmanager.user_server.service.api.notification.INotificationManagerService;
import org.taskmanager.user_server.service.api.notification.INotificationTemplateService;
import org.taskmanager.user_server.service.services.notification.EmailNotificationTemplateService;
import org.taskmanager.user_server.service.services.notification.VerificationTokenService;
import org.taskmanager.user_server.service.services.notification.NotificationManagerService;
import org.thymeleaf.TemplateEngine;

@Configuration
public class NotificationConfig {
    @Bean
    public IVerificationTokenService emailTokenService(IVerificationTokenRepository emailTokenRepository){
        return new VerificationTokenService(emailTokenRepository);
    }
    @Bean
    public INotificationManagerService notificationService(INotificationServiceClient notificationServiceClient, ConversionService conversionService, INotificationTemplateService notificationTemplateService, NotificationProperty notificationProperty, JwtHandler jwtHandler){
        return new NotificationManagerService(notificationServiceClient, conversionService, notificationTemplateService, notificationProperty, jwtHandler);
    }
    @Bean
    public INotificationTemplateService notificationTemplateService(IVerificationTokenService emailTokenService, TemplateEngine templateEngine, NotificationProperty notificationProperty){
        return new EmailNotificationTemplateService(emailTokenService, templateEngine, notificationProperty);
    }
}
