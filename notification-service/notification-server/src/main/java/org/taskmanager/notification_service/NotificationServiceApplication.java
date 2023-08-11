package org.taskmanager.notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.taskmanager.notification_service.config.property.JWTProperty;
import org.taskmanager.notification_service.config.property.MailProperty;

@EnableDiscoveryClient
@EnableConfigurationProperties({MailProperty.class, JWTProperty.class})
@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
