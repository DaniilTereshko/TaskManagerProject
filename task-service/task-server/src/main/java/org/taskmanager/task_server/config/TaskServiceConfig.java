package org.taskmanager.task_server.config;

import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.taskmanager.audit_client.client.api.IAuditServiceClient;
import org.taskmanager.task_server.core.converters.ConversionServiceFactory;
import org.taskmanager.task_server.dao.repositories.IProjectRepository;
import org.taskmanager.task_server.dao.repositories.ITaskRepository;
import org.taskmanager.task_server.endpoints.web.util.JwtHandler;
import org.taskmanager.task_server.service.api.audit.IAuditService;
import org.taskmanager.task_server.service.api.task.IProjectService;
import org.taskmanager.task_server.service.api.task.ITaskService;
import org.taskmanager.task_server.service.api.user.IUserService;
import org.taskmanager.task_server.service.implemetation.audit.AuditService;
import org.taskmanager.task_server.service.implemetation.task.ProjectService;
import org.taskmanager.task_server.service.implemetation.task.TaskService;
import org.taskmanager.task_server.service.implemetation.user.UserHolder;
import org.taskmanager.task_server.service.implemetation.user.UserService;
import org.taskmanager.user_client.client.api.IUserServiceClient;

@Configuration
public class TaskServiceConfig {
    @Bean
    public ConversionServiceFactory conversionService(){
        return new ConversionServiceFactory();
    }

    @Bean
    public IUserService userService(IUserServiceClient userServiceClient, JwtHandler jwtHandler){
        return new UserService(userServiceClient, jwtHandler);
    }
    @Bean
    public IAuditService auditService(IAuditServiceClient auditServiceClient, JwtHandler jwtHandler, UserHolder userHolder){
        return new AuditService(auditServiceClient, jwtHandler, userHolder);
    }
    @Bean
    public IProjectService projectService(IUserService userService, IProjectRepository projectRepository, IAuditService auditService, Validator validator){
        return new ProjectService(userService, projectRepository, auditService, validator);
    }
    @Bean
    public ITaskService taskService(ITaskRepository taskRepository, IProjectService projectService, UserHolder userHolder, IUserService userService, IAuditService auditService, Validator validator){
        return new TaskService(taskRepository, projectService, userHolder, userService, auditService, validator);
    }
}
