package org.taskmanager.user_server.service.services.notification;

import org.springframework.transaction.annotation.Transactional;
import org.taskmanager.user_server.config.properties.NotificationProperty;
import org.taskmanager.user_server.core.exception.NotFoundException;
import org.taskmanager.user_server.dao.entity.User;
import org.taskmanager.user_server.dao.entity.VerificationToken;
import org.taskmanager.user_server.service.api.notification.INotificationTemplateService;
import org.taskmanager.user_server.service.api.notification.IVerificationTokenService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public class EmailNotificationTemplateService implements INotificationTemplateService {
    private static final String TOKEN_BY_USER_NOT_FOUND = "Токен для данного пользователя не найден";
    private final IVerificationTokenService emailTokenService;
    private final TemplateEngine templateEngine;
    private final NotificationProperty notificationProperty;

    public EmailNotificationTemplateService(IVerificationTokenService emailTokenService, TemplateEngine templateEngine, NotificationProperty notificationProperty) {
        this.emailTokenService = emailTokenService;
        this.templateEngine = templateEngine;
        this.notificationProperty = notificationProperty;
    }
    @Transactional(readOnly = true)
    @Override
    public String getVerificationContent(User user) {
        String tokenName = this.notificationProperty.getVerification().getProperties().getTokenName();
        String template = this.notificationProperty.getVerification().getTemplate();

        VerificationToken token = this.emailTokenService.findByUser(user)
                .orElseThrow(() -> new NotFoundException(TOKEN_BY_USER_NOT_FOUND));
        Context context = new Context();
        context.setVariable(tokenName, token);

        return templateEngine.process(template, context);
    }
}
