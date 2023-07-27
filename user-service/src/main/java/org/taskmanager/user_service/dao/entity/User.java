package org.taskmanager.user_service.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.taskmanager.user_service.core.enums.NotificationMethod;
import org.taskmanager.user_service.core.enums.UserRole;
import org.taskmanager.user_service.core.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @NotNull(message = "Идентификатор пользователя обязателен")
    @Id
    private UUID uuid;
    @NotBlank(message = "Ф.И.О. обязательно")
    @Column(name = "fio", nullable = false)
    private String fio;
    @NotNull(message = "Роль пользователя обязателена")
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;
    @NotNull(message = "Статус пользователя обязателен")
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private UserStatus status;
    @NotBlank(message = "Почта обязательна")
    @Email(message = "Почта некорректна")
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "tg", unique = true)
    private String tg;
    @Enumerated(EnumType.STRING)
    @Column(name = "notification_method")
    private NotificationMethod notificationMethod;
    @NotBlank(message = "Пароль обязателен")
    @Column(name = "password", nullable = false)
    private String password;
    @PastOrPresent(message = "Дата создания некорректна")
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "create_date", precision = 3)
    private LocalDateTime createDate;
    @PastOrPresent(message = "Дата обновления некорректна")
    @UpdateTimestamp(source = SourceType.DB)
    @Version
    @Column(name = "update_date", precision = 3)
    private LocalDateTime updateDate;

    public User() {
    }
    public User(String fio, String email, String password, UserRole role, UserStatus status) {
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.email = email;
        this.password = password;
    }

    public User(UUID uuid, String fio, UserRole role, UserStatus status, String email, String password, LocalDateTime updateDate) {
        this.uuid = uuid;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.email = email;
        this.password = password;
        this.updateDate = updateDate;
    }

    public User(UUID uuid, String fio, UserRole role, UserStatus status, String email, String tg, NotificationMethod notificationMethod, String password, LocalDateTime createDate, LocalDateTime updateDate) {
        this.uuid = uuid;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.email = email;
        this.tg = tg;
        this.notificationMethod = notificationMethod;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTg() {
        return tg;
    }

    public void setTg(String tg) {
        this.tg = tg;
    }

    public NotificationMethod getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(NotificationMethod notificationMethod) {
        this.notificationMethod = notificationMethod;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getUuid() != null ? !getUuid().equals(user.getUuid()) : user.getUuid() != null) return false;
        if (getFio() != null ? !getFio().equals(user.getFio()) : user.getFio() != null) return false;
        if (getRole() != user.getRole()) return false;
        if (getStatus() != user.getStatus()) return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getTg() != null ? !getTg().equals(user.getTg()) : user.getTg() != null) return false;
        if (getNotificationMethod() != user.getNotificationMethod()) return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getCreateDate() != null ? !getCreateDate().equals(user.getCreateDate()) : user.getCreateDate() != null)
            return false;
        return getUpdateDate() != null ? getUpdateDate().equals(user.getUpdateDate()) : user.getUpdateDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getUuid() != null ? getUuid().hashCode() : 0;
        result = 31 * result + (getFio() != null ? getFio().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getTg() != null ? getTg().hashCode() : 0);
        result = 31 * result + (getNotificationMethod() != null ? getNotificationMethod().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
        result = 31 * result + (getUpdateDate() != null ? getUpdateDate().hashCode() : 0);
        return result;
    }
}
