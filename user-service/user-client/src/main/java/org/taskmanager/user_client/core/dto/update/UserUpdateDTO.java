package org.taskmanager.user_client.core.dto.update;

import jakarta.validation.constraints.*;
import org.taskmanager.user_client.core.enums.UserRole;
import org.taskmanager.user_client.core.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserUpdateDTO {
    @NotNull(message = "Идентификатор пользователя обязателен")
    private UUID uuid;
    @NotBlank(message = "Ф.И.О. обязательно")
    @Size(max = 255, message = "Максимальный размер Ф.И.О. 255 символов")
    private String fio;
    @NotBlank(message = "Почта обязательна")
    @Size(max = 255, message = "Максимальный размер адреса почты 255 символов")
    @Email(message = "Почта некорректна")
    private String mail;
    @NotNull(message = "Роль пользователя обязателена")
    private UserRole role;
    @NotNull(message = "Статус пользователя обязателен")
    private UserStatus status;
    @NotBlank(message = "Пароль обязателен")
    @Size(min = 5, max = 25, message = "Длина пароля должна быть от 5 до 25 символов")
    private String password;
    private LocalDateTime updateDate;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(UUID uuid, String fio, String mail, UserRole role, UserStatus status, String password, LocalDateTime updateDate) {
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

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
