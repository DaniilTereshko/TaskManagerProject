package org.taskmanager.task_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.taskmanager.task_server.config.property.JWTProperty;

@EnableJpaRepositories
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"org.taskmanager.user_client.client.api", "org.taskmanager.audit_client.client.api"})
@EnableConfigurationProperties({JWTProperty.class})
@SpringBootApplication
public class TaskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskServiceApplication.class, args);
	}

}
