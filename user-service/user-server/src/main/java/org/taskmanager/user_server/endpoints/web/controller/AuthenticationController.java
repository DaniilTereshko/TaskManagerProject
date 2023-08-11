package org.taskmanager.user_server.endpoints.web.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskmanager.user_client.core.dto.base.UserDTO;
import org.taskmanager.user_server.core.dto.base.TokenDTO;
import org.taskmanager.user_server.core.dto.base.UserLoginDTO;
import org.taskmanager.user_server.core.dto.base.UserRegistrationDTO;
import org.taskmanager.user_server.dao.entity.User;
import org.taskmanager.user_server.service.api.user.IAuthenticationService;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class AuthenticationController {
    private final IAuthenticationService authenticationService;
    private final ConversionService conversionService;

    public AuthenticationController(IAuthenticationService authenticationService, ConversionService conversionService) {
        this.authenticationService = authenticationService;
        this.conversionService = conversionService;
    }


    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserRegistrationDTO userRegistrationDTO){
        this.authenticationService.registration(userRegistrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/verification")
    public ResponseEntity<?> verification(@RequestParam UUID code,
                                          @RequestParam String mail) {
        this.authenticationService.verification(mail, code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO){
        TokenDTO dto = this.authenticationService.login(userLoginDTO);
        return new ResponseEntity<>(dto.getToken(), HttpStatus.OK);
    }
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMe() {
        User user = this.authenticationService.getMe();
        UserDTO dto = this.conversionService.convert(user, UserDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
