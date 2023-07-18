package org.taskmanager.notification_service.service.implementation;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.taskmanager.notification_service.core.dto.create.NotificationCreateDTO;
import org.taskmanager.notification_service.core.dto.create.NotificationDTO;
import org.taskmanager.notification_service.service.api.INotificationSenderService;

public class NotificationSenderServiceService implements INotificationSenderService {
    private final JavaMailSender sender;

    public NotificationSenderServiceService(JavaMailSender sender) {
        this.sender = sender;
    }

    @Override
    public NotificationDTO sendSimpleMessage(NotificationCreateDTO notificationCreateDTO) {
        NotificationDTO notificationDTO = new NotificationDTO(notificationCreateDTO.getUser(), notificationCreateDTO.getText(), notificationCreateDTO.getNotificationMethod(), notificationCreateDTO.isRead());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("socialnetwork123@yandex.ru");//TODO props
        message.setTo(notificationDTO.getUser().getMail());
        message.setSubject("Activation letter"); //TODO thymleaf
        message.setText(notificationDTO.getText());
        sender.send(message);
        return notificationDTO;//TODO доделать
    }
}
