package org.taskmanager.notification_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.taskmanager.notification_service.endpoints.web.controllers.ActivateController;

import java.util.Properties;

@Configuration
@EnableWebMvc
public class NotificationServiceConfig {
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("taskmanager213@gmail.com");
        mailSender.setPassword("hkxgqsnzvuawxalv");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
    @Bean
    public ActivateController activateController(JavaMailSender javaMailSender){
        return new ActivateController(javaMailSender);
    }
}
