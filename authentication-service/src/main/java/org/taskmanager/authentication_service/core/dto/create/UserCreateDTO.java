package org.taskmanager.authentication_service.core.dto.create;

public class UserCreateDTO {
    private String fio;
    private String mail;
    private String role;
    private String status;
    private String password;

    public UserCreateDTO() {
    }

    public UserCreateDTO(String fio, String mail, String role, String status, String password) {
        this.fio = fio;
        this.mail = mail;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
