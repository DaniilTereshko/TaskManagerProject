package org.taskmanager.user_server.core.exception;

public class VerificationException extends RuntimeException{
    public VerificationException() {
    }

    public VerificationException(String message) {
        super(message);
    }
}
