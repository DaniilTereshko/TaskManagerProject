package org.taskmanager.notification_service.endpoints.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.notification_service.core.dto.create.NotificationCreateDTO;
import org.taskmanager.notification_service.core.dto.create.NotificationDTO;
import org.taskmanager.notification_service.service.api.INotificationSenderService;

@RestController
@RequestMapping("/sender")
public class ActivateController {
    private final INotificationSenderService notificationSenderService;

    public ActivateController(INotificationSenderService notificationSenderService) {
        this.notificationSenderService = notificationSenderService;
    }

    @PostMapping
    public ResponseEntity<?> activate(@RequestBody NotificationCreateDTO notificationCreateDTO){
        NotificationDTO notificationDTO = notificationSenderService.sendSimpleMessage(notificationCreateDTO);//TOOD вернуть доделать
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
