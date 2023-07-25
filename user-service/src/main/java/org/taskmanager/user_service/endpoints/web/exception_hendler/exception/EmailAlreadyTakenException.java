package org.taskmanager.user_service.endpoints.web.exception_hendler.exception;

public class EmailAlreadyTakenException extends RuntimeException{
    private String email;

    public EmailAlreadyTakenException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
