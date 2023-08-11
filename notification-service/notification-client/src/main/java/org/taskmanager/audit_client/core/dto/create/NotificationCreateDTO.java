package org.taskmanager.audit_client.core.dto.create;

import org.taskmanager.audit_client.core.enums.NotificationMethod;
import org.taskmanager.user_client.core.dto.base.UserDTO;

public class NotificationCreateDTO {
    private String subject;
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

    public NotificationCreateDTO(String subject, UserDTO user, String text, NotificationMethod notificationMethod, boolean isRead) {
        this.subject = subject;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
