package org.taskmanager.user_service.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.taskmanager.user_service.dao.api.IUserRepository;
import org.taskmanager.user_service.service.api.ISendMailService;
import org.taskmanager.user_service.service.api.IUserService;
import org.taskmanager.user_service.service.implemetation.SendMailService;
import org.taskmanager.user_service.service.implemetation.UserService;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class UserServiceConfig {

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    @Bean
    public ISendMailService mailService(){
        return new SendMailService();
    }//TODO Advice exception service
    //TODO сервис отдает page
    @Bean
    public IUserService userService(IUserRepository userRepository, ISendMailService mailService, ConversionService conversionService){
        return new UserService(userRepository, mailService, conversionService);
    }
/*    @Bean
    public UserController userController(IUserService userService, ConversionService conversionService){
        return new UserController(userService, conversionService);
    }*/

}
