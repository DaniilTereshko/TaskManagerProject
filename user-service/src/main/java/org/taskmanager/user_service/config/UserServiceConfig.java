package org.taskmanager.user_service.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.taskmanager.user_service.dao.api.IUserRepository;
import org.taskmanager.user_service.endpoints.web.controller.UserController;
import org.taskmanager.user_service.service.api.ISendMailService;
import org.taskmanager.user_service.service.api.IUserService;
import org.taskmanager.user_service.service.implemetation.SendMailService;
import org.taskmanager.user_service.service.implemetation.UserService;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("org.taskmanager.user_service.dao.api")
@EnableWebMvc
public class UserServiceConfig {
    @Bean
    public DataSource dataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName( "org.postgresql.Driver" );
        dataSource.setJdbcUrl( "jdbc:postgresql://localhost:5432/user_service" );
        dataSource.setUsername("postgres");
        dataSource.setPassword("R2-D2-F1-L1");
        dataSource.setMaximumPoolSize(10);//TODO Advice exception service
        //TODO сервис отдает page

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter hibernate = new HibernateJpaVendorAdapter();
        hibernate.setShowSql(true);


        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setJpaVendorAdapter(hibernate);
        entityManagerFactory.setPackagesToScan("org.taskmanager.user_service.dao.entity");
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        jpaProperties.setProperty("hibernate.default_schema", "user_service");
        entityManagerFactory.setJpaProperties(jpaProperties);

        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    @Bean
    public ISendMailService mailService(){
        return new SendMailService();
    }
    @Bean
    public IUserService userService(IUserRepository userRepository, ISendMailService mailService, ConversionService conversionService){
        return new UserService(userRepository, mailService, conversionService);
    }
    @Bean
    public UserController userController(IUserService userService, ConversionService conversionService){
        return new UserController(userService, conversionService);
    }

}
