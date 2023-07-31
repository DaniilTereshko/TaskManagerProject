package org.taskmanager.user_client.core.dto.create;

import org.taskmanager.user_client.core.enums.UserRole;
import org.taskmanager.user_client.core.enums.UserStatus;

public class UserCreateDTO {
    private String fio;

    private String mail;

    private UserRole role;

    private UserStatus status;

    private String password;

    public UserCreateDTO() {
    }

    public UserCreateDTO(String fio, String mail, UserRole role, UserStatus status) {
        this.fio = fio;
        this.mail = mail;
        this.role = role;
        this.status = status;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
