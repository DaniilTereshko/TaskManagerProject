package org.taskmanager.user_server.config;

import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.taskmanager.audit_client.client.api.IAuditServiceClient;
import org.taskmanager.user_server.core.converters.ConversionServiceFactory;
import org.taskmanager.user_server.dao.repositories.IUserRepository;
import org.taskmanager.user_server.endpoints.web.util.JwtHandler;
import org.taskmanager.user_server.service.api.audit.IAuditService;
import org.taskmanager.user_server.service.api.notification.IVerificationTokenService;
import org.taskmanager.user_server.service.api.notification.INotificationManagerService;
import org.taskmanager.user_server.service.api.user.IAuthenticationService;
import org.taskmanager.user_server.service.api.user.IUserService;
import org.taskmanager.user_server.service.services.audit.AuditService;
import org.taskmanager.user_server.service.services.user.AuthenticationService;
import org.taskmanager.user_server.service.services.user.UserHolder;
import org.taskmanager.user_server.service.services.user.UserService;

@Configuration
public class UserServiceConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public ConversionServiceFactory conversionService(){
        return new ConversionServiceFactory();
    }
    @Bean
    public IAuditService auditService(IAuditServiceClient auditServiceClient, JwtHandler jwtHandler, UserHolder userHolder){
        return new AuditService(auditServiceClient, jwtHandler, userHolder);
    }
    @Bean
    public IUserService userService(IUserRepository userRepository, IAuditService auditService, Validator validator, PasswordEncoder passwordEncoder, ConversionService conversionService){
        return new UserService(userRepository, auditService, validator, passwordEncoder, conversionService);
    }
    @Bean
    public IAuthenticationService authenticationService(IUserService userService, PasswordEncoder passwordEncoder, JwtHandler jwtHandler, UserHolder userHolder, IVerificationTokenService emailTokenService, INotificationManagerService notificationService,  Validator validator){
        return new AuthenticationService(userService, passwordEncoder, jwtHandler, userHolder, emailTokenService, notificationService, validator);
    }
}
