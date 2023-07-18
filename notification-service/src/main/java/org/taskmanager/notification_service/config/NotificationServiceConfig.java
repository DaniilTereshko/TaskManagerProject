package org.taskmanager.notification_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.taskmanager.notification_service.service.api.INotificationSenderService;
import org.taskmanager.notification_service.service.implementation.NotificationSenderServiceService;

@Configuration
public class NotificationServiceConfig {
    @Bean
    public INotificationSenderService notificationSenderService(JavaMailSender javaMailSender){
        return new NotificationSenderServiceService(javaMailSender);
    }
}
