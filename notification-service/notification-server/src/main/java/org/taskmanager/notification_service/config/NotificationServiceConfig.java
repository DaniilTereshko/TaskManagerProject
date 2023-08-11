package org.taskmanager.notification_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.taskmanager.notification_service.config.property.MailProperty;
import org.taskmanager.notification_service.service.api.INotificationService;
import org.taskmanager.notification_service.service.implementation.EmailNotificationService;

@Configuration
public class NotificationServiceConfig {
    @Bean
    public INotificationService notificationSenderService(JavaMailSender javaMailSender, MailProperty mailProperty){
        return new EmailNotificationService(javaMailSender, mailProperty);
    }
}
