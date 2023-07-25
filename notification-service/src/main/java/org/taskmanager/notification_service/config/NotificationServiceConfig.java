package org.taskmanager.notification_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.taskmanager.notification_service.service.api.INotificationService;
import org.taskmanager.notification_service.service.implementation.NotificationService;

@Configuration
public class NotificationServiceConfig {
    @Bean
    public INotificationService notificationSenderService(JavaMailSender javaMailSender){
        return new NotificationService(javaMailSender);
    }
}
