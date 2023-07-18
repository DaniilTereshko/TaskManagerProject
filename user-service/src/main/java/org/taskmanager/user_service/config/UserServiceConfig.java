package org.taskmanager.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.taskmanager.user_service.config.property.NotificationServiceProperty;
import org.taskmanager.user_service.dao.api.IUserRepository;
import org.taskmanager.user_service.service.api.ISendMailService;
import org.taskmanager.user_service.service.api.IUserService;
import org.taskmanager.user_service.service.implemetation.SendMailService;
import org.taskmanager.user_service.service.implemetation.UserService;

@Configuration
public class UserServiceConfig {
    @Bean
    public ISendMailService mailService(NotificationServiceProperty notificationServiceProperty){
        return new SendMailService(notificationServiceProperty);
    }//TODO Advice exception service
    //TODO сервис отдает page
    @Bean
    public IUserService userService(IUserRepository userRepository, ISendMailService mailService, ConversionService conversionService){
        return new UserService(userRepository, mailService, conversionService);
    }
}
