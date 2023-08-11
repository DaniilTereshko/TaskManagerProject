package org.taskmanager.task_server.core.exception;

public class InvalidEmployeeException extends RuntimeException{
    private String field;
    public InvalidEmployeeException() {
    }

    public InvalidEmployeeException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
