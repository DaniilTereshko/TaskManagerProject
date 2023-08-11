package org.taskmanager.user_server.service.api.notification;

import org.taskmanager.user_server.dao.entity.User;

public interface INotificationTemplateService {
    String getVerificationContent(User user);
}
