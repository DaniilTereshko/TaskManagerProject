package org.taskmanager.notification_service.service.api;

import org.taskmanager.notification_service.core.dto.create.NotificationCreateDTO;
import org.taskmanager.notification_service.core.dto.create.NotificationDTO;

public interface INotificationSenderService {
    NotificationDTO sendSimpleMessage(NotificationCreateDTO notificationCreateDTO);
}
