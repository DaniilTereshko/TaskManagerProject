package org.taskmanager.authentication_service.service.feign_clients.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.taskmanager.authentication_service.core.dto.create.NotificationCreateDTO;

@FeignClient(name = "notification-service")
public interface INotificationServiceClient {
    @PostMapping("/sender")
    ResponseEntity<?> activate(@RequestBody NotificationCreateDTO notificationCreateDTO);
}
