package org.taskmanager.notification_service.endpoints.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.notification_service.core.NotificationCreateDTO;

import java.util.UUID;

@RestController
@RequestMapping("/activate")
public class ActivateController {
    private final JavaMailSender sender;

    public ActivateController(JavaMailSender sender) {
        this.sender = sender;
    }

    @PostMapping
    public ResponseEntity<?> activate(@RequestBody NotificationCreateDTO notificationCreateDTO){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("socialnetwork123@yandex.ru");
        message.setTo(notificationCreateDTO.getUser().getMail());
        message.setSubject("Activation letter");
        message.setText(notificationCreateDTO.getText());
        sender.send(message);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
