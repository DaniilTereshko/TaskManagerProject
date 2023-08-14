package org.taskmanager.report_server.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.taskmanager.audit_client.client.api.IAuditServiceClient;
import org.taskmanager.report_server.config.properties.MinioProperties;
import org.taskmanager.report_server.dao.repositories.IReportRepository;
import org.taskmanager.report_server.endpoints.web.util.JwtHandler;
import org.taskmanager.report_server.service.api.audit.IAuditService;
import org.taskmanager.report_server.service.api.report.IAuditReportService;
import org.taskmanager.report_server.service.api.report.IReportGenerationService;
import org.taskmanager.report_server.service.api.report.IReportService;
import org.taskmanager.report_server.service.api.user.IUserService;
import org.taskmanager.report_server.service.implementation.audit.AuditService;
import org.taskmanager.report_server.service.implementation.report.AuditReportService;
import org.taskmanager.report_server.service.implementation.report.MinioAdapter;
import org.taskmanager.report_server.service.implementation.report.ReportService;
import org.taskmanager.report_server.service.implementation.user.UserService;
import org.taskmanager.report_server.service.implementation.report.XmlReportGenerationService;
import org.taskmanager.user_client.client.api.IUserServiceClient;

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
    @Bean
    public IAuditService auditService(IAuditServiceClient auditServiceClient, JwtHandler jwtHandler) {
        return new AuditService(auditServiceClient, jwtHandler);
    }
    @Bean
    public IReportGenerationService reportGenerationService(){
        return new XmlReportGenerationService();
    }
    @Bean
    public MinioAdapter reportStorageService(MinioClient minioClient, MinioProperties minioProperties){
        return new MinioAdapter(minioClient, minioProperties);
    }
    @Bean
    public IReportService reportService(IReportRepository reportRepository, IAuditService auditService, MinioAdapter minioAdapter, IAuditReportService auditReportService, ConversionService conversionService){
        return new ReportService(reportRepository, auditService, minioAdapter, auditReportService, conversionService);
    }
    @Bean
    IAuditReportService auditReportService(IReportGenerationService reportGenerationService, MinioAdapter minioAdapter, ConversionService conversionService){
        return new AuditReportService(reportGenerationService, minioAdapter, conversionService);
    }
    @Bean
    public IUserService userService(IUserServiceClient userServiceClient){
        return new UserService(userServiceClient);
    }
}
