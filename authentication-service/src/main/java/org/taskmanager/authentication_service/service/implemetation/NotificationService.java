package org.taskmanager.authentication_service.service.implemetation;

import org.taskmanager.authentication_service.core.dto.base.UserDTO;
import org.taskmanager.authentication_service.core.dto.create.NotificationCreateDTO;
import org.taskmanager.authentication_service.core.enums.NotificationMethod;
import org.taskmanager.authentication_service.service.api.INotificationService;
import org.taskmanager.authentication_service.service.feign_clients.api.INotificationServiceClient;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.UUID;

public class NotificationService implements INotificationService {
    private final INotificationServiceClient notificationServiceFeignClient;
    private final TemplateEngine templateEngine;


    public NotificationService(INotificationServiceClient notificationServiceFeignClient, TemplateEngine templateEngine) {
        this.notificationServiceFeignClient = notificationServiceFeignClient;
        this.templateEngine = templateEngine;
    }

    @Override
    public UUID sendActivateMail(UserDTO userDTO) {
        NotificationCreateDTO notificationCreateDTO = new NotificationCreateDTO(userDTO, NotificationMethod.EMAIL, false);
        UUID uuidMail = UUID.randomUUID();

        Context context = new Context();
        context.setVariable("name", userDTO.getFio());
        context.setVariable("code", uuidMail);
        context.setVariable("email", userDTO.getMail()); //TODO перенести текст в props
        String text = templateEngine.process("activation-email-template.txt", context);

        notificationCreateDTO.setText(text);

        notificationServiceFeignClient.activate(notificationCreateDTO);
        return uuidMail;
    }
}
