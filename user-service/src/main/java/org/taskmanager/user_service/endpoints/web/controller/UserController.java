package org.taskmanager.user_service.endpoints.web.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.user_service.core.dto.base.UserDTO;
import org.taskmanager.user_service.core.dto.base.UserPageDTO;
import org.taskmanager.user_service.core.dto.base.UserRegistrationDTO;
import org.taskmanager.user_service.core.dto.create.UserCreateDTO;
import org.taskmanager.user_service.core.dto.update.UserUpdateDTO;
import org.taskmanager.user_service.dao.entity.User;
import org.taskmanager.user_service.service.api.IUserService;

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
    public ResponseEntity<UserPageDTO> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size){

        PageRequest pageRequest = PageRequest.of(page, size);
        UserPageDTO userPageDTO = userService.get(pageRequest);
        return new ResponseEntity<>(userPageDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDTO> findById(@PathVariable UUID uuid){
        User user = userService.get(uuid);
        UserDTO userDTO = this.conversionService.convert(user, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<UserDTO> update(
            @PathVariable UUID uuid,
            @PathVariable("dt_update") Long updateDate,//TODO не лонг
            @RequestBody UserUpdateDTO userUpdateDTO){ //TODO разбить на второй микросверис
        //TODO feign

        userUpdateDTO.setUpdateDate(updateDate);
        userUpdateDTO.setUuid(uuid);//TODO возвращение

        User updatedUser = userService.update(userUpdateDTO);
        UserDTO userDTO = this.conversionService.convert(updatedUser, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserRegistrationDTO userRegistrationDTO){
        userService.registration(userRegistrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/verification")
    public ResponseEntity<?> verification(@RequestParam String code,
                                          @RequestParam String mail) {

        boolean isActivated = userService.activate(mail, code);//TODO !!!!
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
