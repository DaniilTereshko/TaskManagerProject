package org.taskmanager.user_server.core.exception;

public class EmailAlreadyTakenException extends RuntimeException{
    private final String field;

    public EmailAlreadyTakenException(String message) {
        super(message);
        this.field = null;
    }

    public EmailAlreadyTakenException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
