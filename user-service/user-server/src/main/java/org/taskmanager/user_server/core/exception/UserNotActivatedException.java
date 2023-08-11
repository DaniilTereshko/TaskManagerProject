package org.taskmanager.user_server.core.exception;

public class UserNotActivatedException extends RuntimeException{
    public UserNotActivatedException() {
    }

    public UserNotActivatedException(String message) {
        super(message);
    }
}
