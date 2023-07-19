package org.taskmanager.user_service.service.services.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.taskmanager.user_service.core.dto.create.NotificationCreateDTO;

@FeignClient(name = "notification-service")
public interface INotificationServiceFeignClient {
    @PostMapping("/sender")
    ResponseEntity<?> activate(@RequestBody NotificationCreateDTO notificationCreateDTO);
}
