package org.taskmanager.user_service.service.implemetation;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.taskmanager.user_service.config.property.NotificationServiceProperty;
import org.taskmanager.user_service.core.dto.create.NotificationCreateDTO;
import org.taskmanager.user_service.core.enums.NotificationMethod;
import org.taskmanager.user_service.core.dto.base.UserDTO;
import org.taskmanager.user_service.service.api.ISendMailService;
import org.taskmanager.user_service.service.services.api.INotificationServiceFeignClient;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.UUID;

public class SendMailService implements ISendMailService {
    private final INotificationServiceFeignClient notificationServiceFeignClient;
    private final TemplateEngine templateEngine;


    public SendMailService(INotificationServiceFeignClient notificationServiceFeignClient, TemplateEngine templateEngine) {
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
        context.setVariable("email", userDTO.getMail()); //TODO перенести текст в настройки
        String text = templateEngine.process("activation-email-template.txt", context);

        notificationCreateDTO.setText(text);
        //TODO ??? //Load balancer does not contain an instance for the service notification-service
        notificationServiceFeignClient.activate(notificationCreateDTO);
        return uuidMail;
    }
}
