package org.taskmanager.user_server.endpoints.web.exception_hendler.exception;

import java.util.UUID;

public class NoSuchUserException extends RuntimeException{
    private UUID uuid;

    public NoSuchUserException() {
    }

    public NoSuchUserException(UUID uuid) {
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
