package org.taskmanager.notification_service.core.dto.base;

import java.util.UUID;

public class UserDTO {
    private UUID uuid;
    private String fio;
    private String mail;
    private String role;
    private String status;
    private Long createDate;
    private Long updateDate;

    public UserDTO() {
    }

    public UserDTO(String fio, String mail, String role, String status) {
        this.fio = fio;
        this.mail = mail;
        this.role = role;
        this.status = status;
    }

    public UserDTO(UUID uuid, String fio, String mail, String role, String status, Long createDate, Long updateDate) {
        this.uuid = uuid;
        this.fio = fio;
        this.mail = mail;
        this.role = role;
        this.status = status;
        this.createDate = createDate;
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

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
