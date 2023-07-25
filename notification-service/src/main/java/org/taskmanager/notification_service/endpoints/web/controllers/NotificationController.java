package org.taskmanager.notification_service.endpoints.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.notification_service.core.dto.create.NotificationCreateDTO;
import org.taskmanager.notification_service.core.dto.create.NotificationDTO;
import org.taskmanager.notification_service.service.api.INotificationService;

@RestController
@RequestMapping("/sender")
public class NotificationController {
    private final INotificationService notificationSenderService;

    public NotificationController(INotificationService notificationSenderService) {
        this.notificationSenderService = notificationSenderService;
    }

    @PostMapping
    public ResponseEntity<?> activate(@RequestBody NotificationCreateDTO notificationCreateDTO){
        NotificationDTO notificationDTO = notificationSenderService.sendSimpleMessage(notificationCreateDTO);//TOOD вернуть доделать
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
