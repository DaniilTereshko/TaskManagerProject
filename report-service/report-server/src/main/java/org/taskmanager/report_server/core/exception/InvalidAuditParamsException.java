package org.taskmanager.report_server.core.exception;

public class InvalidAuditParamsException extends RuntimeException{
    private String field;

    public InvalidAuditParamsException() {
    }

    public InvalidAuditParamsException(String message) {
        super(message);
    }
    public InvalidAuditParamsException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
