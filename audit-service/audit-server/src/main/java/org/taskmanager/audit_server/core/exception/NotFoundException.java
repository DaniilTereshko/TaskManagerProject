package org.taskmanager.audit_server.core.exception;

public class NotFoundException extends RuntimeException{
    private String field;
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
