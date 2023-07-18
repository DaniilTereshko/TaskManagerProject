package org.taskmanager.user_service.core.dto.update;

import java.util.UUID;

public class UserUpdateDTO {
    private UUID uuid;
    private String fio;
    private String mail;
    private String role;
    private String status;
    private String password;
    private Long updateDate;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(UUID uuid, String fio, String mail, String role, String status, String password, Long updateDate) {
        this.uuid = uuid;
        this.fio = fio;
        this.mail = mail;
        this.role = role;
        this.status = status;
        this.password = password;
        this.updateDate = updateDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
