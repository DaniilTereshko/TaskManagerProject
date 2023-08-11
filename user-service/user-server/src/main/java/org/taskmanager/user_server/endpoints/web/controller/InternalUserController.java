package org.taskmanager.user_server.endpoints.web.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taskmanager.user_client.core.dto.base.UserDTO;
import org.taskmanager.user_server.dao.entity.User;
import org.taskmanager.user_server.service.api.user.IUserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/internal/users")
public class InternalUserController {
    private final IUserService userService;
    private final ConversionService conversionService;

    public InternalUserController(IUserService userService, ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<List<UserDTO>> get(@RequestBody List<UUID> users){
        List<User> list = this.userService.get(users);
        List<UserDTO> dtos = list.stream().map(u -> conversionService.convert(u, UserDTO.class)).toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
