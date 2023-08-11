package org.taskmanager.user_server.core.dto.base;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {
    @NotBlank(message = "Почта обязательна")
    @Size(max = 255, message = "Максимальный размер адреса почты 255 символов")
    @Email(message = "Почта некорректна")
    private String mail;
    @NotBlank(message = "Ф.И.О. обязательно")
    @Size(max = 255, message = "Максимальный размер Ф.И.О. 255 символов")
    private String fio;
    @NotBlank(message = "Пароль обязателен")
    @Size(min = 5, max = 25, message = "Длина пароля должна быть от 5 до 25 символов")
    private String password;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String mail, String fio, String password) {
        this.mail = mail;
        this.fio = fio;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
