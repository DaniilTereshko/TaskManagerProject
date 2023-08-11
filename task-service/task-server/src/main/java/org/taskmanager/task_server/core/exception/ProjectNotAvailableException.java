package org.taskmanager.task_server.core.exception;

public class ProjectNotAvailableException extends RuntimeException{
    public ProjectNotAvailableException() {
    }

    public ProjectNotAvailableException(String message) {
        super(message);
    }
}
