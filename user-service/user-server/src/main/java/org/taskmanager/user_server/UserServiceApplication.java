package org.taskmanager.user_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.taskmanager.user_server.config.properties.JWTProperty;
import org.taskmanager.user_server.config.properties.NotificationProperty;

@EnableJpaRepositories
@EnableTransactionManagement
@EnableFeignClients(basePackages = {"org.taskmanager.audit_client.client.api", "org.taskmanager.notification_client.client.api"})
@EnableDiscoveryClient
@EnableConfigurationProperties({JWTProperty.class, NotificationProperty.class})
@SpringBootApplication
public class UserServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
