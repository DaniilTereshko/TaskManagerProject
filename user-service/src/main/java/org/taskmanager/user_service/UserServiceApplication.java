package org.taskmanager.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.taskmanager.user_service.config.property.NotificationServiceProperty;
@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication
@EnableConfigurationProperties(NotificationServiceProperty.class)
public class UserServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}//TODO вариация админ добавления

}
