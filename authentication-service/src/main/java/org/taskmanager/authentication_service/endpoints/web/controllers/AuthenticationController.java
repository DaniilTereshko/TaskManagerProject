package org.taskmanager.authentication_service.endpoints.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.authentication_service.core.dto.base.UserRegistrationDTO;
import org.taskmanager.authentication_service.service.api.IUserService;

@RestController
public class AuthenticationController {
    private final IUserService userService;

    public AuthenticationController(IUserService userService) {
        this.userService = userService;
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
