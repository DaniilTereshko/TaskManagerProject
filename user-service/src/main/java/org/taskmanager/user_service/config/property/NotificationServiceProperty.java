package org.taskmanager.user_service.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("notification-service")
public class NotificationServiceProperty {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
