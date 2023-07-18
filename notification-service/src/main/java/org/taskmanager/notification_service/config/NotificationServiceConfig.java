package org.taskmanager.notification_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.taskmanager.notification_service.endpoints.web.controllers.ActivateController;

import java.util.Properties;

@Configuration
public class NotificationServiceConfig {

}
