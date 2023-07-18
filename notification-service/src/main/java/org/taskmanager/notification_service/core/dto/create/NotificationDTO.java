package org.taskmanager.notification_service.core.dto.create;

import org.taskmanager.notification_service.core.dto.base.UserDTO;
import org.taskmanager.notification_service.core.enums.NotificationMethod;

public class NotificationDTO {
    private UserDTO user;
    private String text;
    private NotificationMethod notificationMethod;
    private boolean isRead;

    public NotificationDTO() {
    }

    public NotificationDTO(UserDTO user, NotificationMethod notificationMethod, boolean isRead) {
        this.user = user;
        this.notificationMethod = notificationMethod;
        this.isRead = isRead;
    }

    public NotificationDTO(UserDTO user, String text, NotificationMethod notificationMethod, boolean isRead) {
        this.user = user;
        this.text = text;
        this.notificationMethod = notificationMethod;
        this.isRead = isRead;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public NotificationMethod getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(NotificationMethod notificationMethod) {
        this.notificationMethod = notificationMethod;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
