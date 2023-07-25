package org.taskmanager.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.taskmanager.user_service.core.converters.ConversionServiceFactory;
import org.taskmanager.user_service.dao.repositories.IUserRepository;
import org.taskmanager.user_service.service.api.IUserService;
import org.taskmanager.user_service.service.services.UserService;

@Configuration
public class UserServiceConfig {

    @Bean
    public ConversionServiceFactory conversionService(){
        return new ConversionServiceFactory();
    }
    @Bean
    public IUserService userService(IUserRepository userRepository, ConversionService conversionService){
        return new UserService(userRepository, conversionService);
    }
}
