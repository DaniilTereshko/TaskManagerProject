package org.taskmanager.audit_client.core.dto.base;

import org.taskmanager.user_client.core.enums.UserRole;

import java.util.UUID;

public class UserDTO {
    private UUID uuid;
    private String mail;
    private String fio;
    private UserRole role;

    public UserDTO() {
    }

    public UserDTO(UUID uuid, String mail, String fio, UserRole role) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
