package org.taskmanager.user_server.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "notifications")
public class NotificationProperty {
    private Verification verification;

    public static class Verification {
        private String subject;
        private String template;
        private Properties properties;

        public String getSubject() {
            return subject;
        }
        public String getTemplate() {
            return template;
        }
        public Properties getProperties() {
            return properties;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public void setProperties(Properties properties) {
            this.properties = properties;
        }

        public static class Properties {
            private String tokenName;

            public String getTokenName() {
                return tokenName;
            }

            public void setTokenName(String tokenName) {
                this.tokenName = tokenName;
            }
        }
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }
}
