package org.taskmanager.user_server.service.services.notification;


import org.springframework.core.convert.ConversionService;
import org.taskmanager.audit_client.client.api.INotificationServiceClient;
import org.taskmanager.audit_client.core.dto.create.NotificationCreateDTO;
import org.taskmanager.audit_client.core.enums.NotificationMethod;
import org.taskmanager.user_client.core.dto.base.UserDTO;
import org.taskmanager.user_server.config.properties.NotificationProperty;
import org.taskmanager.user_server.dao.entity.User;
import org.taskmanager.user_server.endpoints.web.util.JwtHandler;
import org.taskmanager.user_server.service.api.notification.INotificationManagerService;
import org.taskmanager.user_server.service.api.notification.INotificationTemplateService;

public class NotificationManagerService implements INotificationManagerService {
    private final INotificationTemplateService notificationTemplateService;
    private final INotificationServiceClient notificationServiceClient;
    private final ConversionService conversionService;
    private final NotificationProperty notificationProperty;
    private final JwtHandler jwtHandler;

    public NotificationManagerService(INotificationServiceClient notificationServiceClient, ConversionService conversionService, INotificationTemplateService notificationTemplateService, NotificationProperty notificationProperty, JwtHandler jwtHandler) {
        this.notificationTemplateService = notificationTemplateService;
        this.notificationServiceClient = notificationServiceClient;
        this.conversionService = conversionService;
        this.notificationProperty = notificationProperty;
        this.jwtHandler = jwtHandler;
    }
    @Override
    public void sendVerificationMail(User item) {
        String jwt = this.jwtHandler.generateSystemAccessToken("notification-service");
        String subject = this.notificationProperty.getVerification().getSubject();
        String content = this.notificationTemplateService.getVerificationContent(item);
        UserDTO user = this.conversionService.convert(item, UserDTO.class);
        NotificationCreateDTO dto = new NotificationCreateDTO(subject, user,
                content, NotificationMethod.EMAIL, false);
        this.notificationServiceClient.send("Bearer " + jwt, dto);
    }

}
