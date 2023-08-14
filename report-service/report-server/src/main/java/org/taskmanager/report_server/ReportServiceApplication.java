package org.taskmanager.report_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.taskmanager.report_server.config.properties.JWTProperty;
import org.taskmanager.report_server.config.properties.MinioProperties;
@EnableJpaRepositories
@EnableTransactionManagement
@EnableFeignClients(basePackages = {"org.taskmanager.audit_client.client.api", "org.taskmanager.user_client.client.api"})
@EnableDiscoveryClient
@EnableConfigurationProperties({MinioProperties.class, JWTProperty.class})
@SpringBootApplication
public class ReportServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReportServiceApplication.class, args);
	}

}
