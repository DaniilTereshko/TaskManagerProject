package org.taskmanager.report_server.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.taskmanager.report_server.config.properties.MinioProperties;

@Configuration
public class ReportServiceConfig {
    private final MinioProperties minioProperties;

    public ReportServiceConfig(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
