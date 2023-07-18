package org.taskmanager.notification_service.core;

import java.util.UUID;

public class NotificationCreateDTO {
    private UserDTO user;
    private String text;
    private NotificationMethod notificationMethod;
    private boolean isRead;

    public NotificationCreateDTO() {
    }

    public NotificationCreateDTO(UserDTO user, NotificationMethod notificationMethod, boolean isRead) {
        this.user = user;
        this.notificationMethod = notificationMethod;
        this.isRead = isRead;
    }

    public NotificationCreateDTO(UserDTO user, String text, NotificationMethod notificationMethod, boolean isRead) {
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
