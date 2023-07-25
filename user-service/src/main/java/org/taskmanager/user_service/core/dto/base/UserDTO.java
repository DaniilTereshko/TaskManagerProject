package org.taskmanager.user_service.core.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.taskmanager.user_service.core.enums.UserRole;
import org.taskmanager.user_service.core.enums.UserStatus;

import java.util.UUID;

public class UserDTO {
    private UUID uuid;
    private String fio;
    private String mail;
    private UserRole role;
    private UserStatus status;
    @JsonProperty("dt_create")
    private Long createDate;
    @JsonProperty("dt_update")
    private Long updateDate;

    public UserDTO() {
    }

    public UserDTO(UUID uuid, String fio, String mail, UserRole role, UserStatus status) {
        this.uuid = uuid;
        this.fio = fio;
        this.mail = mail;
        this.role = role;
        this.status = status;
    }

    public UserDTO(UUID uuid, String fio, String mail, UserRole role, UserStatus status, Long updateDate) {
        this.uuid = uuid;
        this.fio = fio;
        this.mail = mail;
        this.role = role;
        this.status = status;
        this.updateDate = updateDate;
    }

    public UserDTO(String fio, String mail, UserRole role, UserStatus status) {
        this.fio = fio;
        this.mail = mail;
        this.role = role;
        this.status = status;
    }

    public UserDTO(UUID uuid, String fio, String mail, UserRole role, UserStatus status, Long createDate, Long updateDate) {
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
