package org.taskmanager.user_server.endpoints.web.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.base_package.dto.PageDTO;
import org.taskmanager.user_client.core.dto.base.UserDTO;
import org.taskmanager.user_client.core.dto.create.UserCreateDTO;
import org.taskmanager.user_client.core.dto.update.UserUpdateDTO;
import org.taskmanager.user_server.core.util.PageToPageDTOUtil;
import org.taskmanager.user_server.dao.entity.User;
import org.taskmanager.user_server.service.api.IUserService;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final ConversionService conversionService;

    public UserController(IUserService userService, ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> save(@RequestBody UserCreateDTO userCreateDTO){
        User savedUser = this.userService.save(userCreateDTO);
        UserDTO userDTO = this.conversionService.convert(savedUser, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<PageDTO<UserDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size){
        Page<User> users = userService.get(page, size);
        PageDTO<UserDTO> userPageDTO = PageToPageDTOUtil.convert(users, conversionService);
        return new ResponseEntity<>(userPageDTO, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDTO> findById(@PathVariable UUID uuid){
        User user = userService.get(uuid);
        UserDTO userDTO = this.conversionService.convert(user, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<UserDTO> update(
            @PathVariable UUID uuid,
            @PathVariable("dt_update") LocalDateTime updateDate,
            @RequestBody UserUpdateDTO userUpdateDTO){
        userUpdateDTO.setUpdateDate(updateDate);
        userUpdateDTO.setUuid(uuid);
        User updatedUser = userService.update(userUpdateDTO);
        UserDTO userDTO = this.conversionService.convert(updatedUser, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
