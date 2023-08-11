package org.taskmanager.user_client.client.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.user_client.core.dto.base.UserDTO;
import org.taskmanager.user_client.core.dto.base.UserDetailsDTO;
import org.taskmanager.user_client.core.dto.create.UserCreateDTO;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "user-service")
public interface IUserServiceClient {
    @GetMapping("/users/me")
    ResponseEntity<UserDTO> getMe(@RequestHeader("Authorization") String jwt);
    @GetMapping("/users/{uuid}")
    ResponseEntity<UserDTO> get(@RequestHeader("Authorization") String jwt, @PathVariable UUID uuid);
    @PostMapping("/internal/users")
    ResponseEntity<List<UserDTO>> get(@RequestHeader("Authorization") String jwt, @RequestBody List<UUID> users);

}
