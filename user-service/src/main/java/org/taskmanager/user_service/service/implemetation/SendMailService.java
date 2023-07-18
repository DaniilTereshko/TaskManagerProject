package org.taskmanager.user_service.service.implemetation;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.taskmanager.user_service.config.property.NotificationServiceProperty;
import org.taskmanager.user_service.core.dto.create.NotificationCreateDTO;
import org.taskmanager.user_service.core.enums.NotificationMethod;
import org.taskmanager.user_service.core.dto.base.UserDTO;
import org.taskmanager.user_service.service.api.ISendMailService;

import java.util.UUID;

public class SendMailService implements ISendMailService {
    private static final String EMAIL_SERVICE_URL = "http://localhost:81/activate";//TODO убрать
    private final NotificationServiceProperty notificationServiceProperty;
    private RestTemplate restTemplate = new RestTemplate();//TODO bean??

    public SendMailService(NotificationServiceProperty notificationServiceProperty) {
        this.notificationServiceProperty = notificationServiceProperty;
    }

    @Override
    public UUID sendActivateMail(UserDTO userDTO) {
        NotificationCreateDTO notificationCreateDTO = new NotificationCreateDTO(userDTO, NotificationMethod.EMAIL, false);
        String mailText = "Hello %s! This is your activation url: %s";
        UUID uuidMail = UUID.randomUUID();
        String text = String.format(mailText, userDTO.getFio(), notificationServiceProperty.getUrl());
        notificationCreateDTO.setText(text);
        //TODO ??? //Thymleaf //openfien
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<NotificationCreateDTO> requestEntity = new HttpEntity<>(notificationCreateDTO, headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(EMAIL_SERVICE_URL, HttpMethod.POST, requestEntity, Void.class);
        System.out.println("УРА");
        return uuidMail;
    }
}
