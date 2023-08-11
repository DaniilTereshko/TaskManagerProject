package org.taskmanager.notification_service.endpoints.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taskmanager.audit_client.core.dto.create.NotificationCreateDTO;
import org.taskmanager.notification_service.service.api.INotificationService;

@RestController
@RequestMapping("/internal/notification")
public class InternalNotificationController {
    private final INotificationService notificationService;

    public InternalNotificationController(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<?> send(@RequestBody NotificationCreateDTO notificationCreateDTO){
        this.notificationService.sendNotification(notificationCreateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
