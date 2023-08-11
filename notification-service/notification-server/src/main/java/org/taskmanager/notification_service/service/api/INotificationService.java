package org.taskmanager.notification_service.service.api;

import org.taskmanager.audit_client.core.dto.create.NotificationCreateDTO;

public interface INotificationService {
    void sendNotification(NotificationCreateDTO notificationCreateDTO);
}
