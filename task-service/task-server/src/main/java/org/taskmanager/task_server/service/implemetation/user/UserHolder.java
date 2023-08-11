package org.taskmanager.task_server.service.implemetation.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.taskmanager.user_client.core.dto.base.UserDTO;

@Component
public class UserHolder {

    public UserDTO getUser(){
        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
