package org.taskmanager.audit_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.taskmanager.audit_server.config.property.JWTProperty;

@EnableJpaRepositories
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"org.taskmanager.user_client.client.api"})
@EnableConfigurationProperties({JWTProperty.class})
@ComponentScan(basePackages = "org.taskmanager.audit_server")
public class AuditServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditServiceApplication.class, args);
	}

}
