package org.taskmanager.audit_server.endpoints.web.exception_hendler.exception;

import java.util.UUID;

public class NoSuchAuditException extends RuntimeException{
    private UUID uuid;

    public NoSuchAuditException() {
    }

    public NoSuchAuditException(UUID uuid) {
        super();
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
