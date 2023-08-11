package org.taskmanager.notification_service.service.implementation;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.taskmanager.audit_client.core.dto.create.NotificationCreateDTO;
import org.taskmanager.notification_service.config.property.MailProperty;
import org.taskmanager.notification_service.service.api.INotificationService;

import java.nio.charset.StandardCharsets;

public class EmailNotificationService implements INotificationService {
    private final JavaMailSender sender;
    private final MailProperty.Mail mailProperty;

    public EmailNotificationService(JavaMailSender sender, MailProperty mailProperty) {
        this.sender = sender;
        this.mailProperty = mailProperty.getMail();
    }

    @Override
    public void sendNotification(NotificationCreateDTO dto) {
        MimeMessage mimeMessage = this.sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setSubject(dto.getSubject());
            helper.setFrom(this.mailProperty.getUsername());
            helper.setTo(dto.getUser().getMail());
            helper.setText(dto.getText(), true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        this.sender.send(mimeMessage);
    }
}
