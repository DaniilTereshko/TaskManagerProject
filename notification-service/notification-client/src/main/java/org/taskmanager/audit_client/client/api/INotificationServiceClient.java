package org.taskmanager.audit_client.client.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.taskmanager.audit_client.core.dto.create.NotificationCreateDTO;

@FeignClient(name = "notification-service")
public interface INotificationServiceClient {
    @PostMapping("/internal/notification")
    ResponseEntity<?> send(@RequestHeader("Authorization") String jwt,
                           @RequestBody NotificationCreateDTO notificationCreateDTO);
}
