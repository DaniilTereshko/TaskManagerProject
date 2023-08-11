package org.taskmanager.user_server.dao.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "verification_tokens")
public class VerificationToken {
    @Id
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "token", nullable = false, unique = true)
    private UUID token;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @OneToOne
    @JoinColumn(name="user_id", nullable = false, unique = true)
    private User user;

    public VerificationToken() {
    }

    public VerificationToken(UUID token, String email, User user) {
        this.token = token;
        this.email = email;
        this.user = user;
    }

    public VerificationToken(UUID uuid, UUID token, String email, User user) {
        this.uuid = uuid;
        this.token = token;
        this.email = email;
        this.user = user;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerificationToken that = (VerificationToken) o;

        if (getUuid() != null ? !getUuid().equals(that.getUuid()) : that.getUuid() != null) return false;
        if (getToken() != null ? !getToken().equals(that.getToken()) : that.getToken() != null) return false;
        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
        return getUser() != null ? getUser().equals(that.getUser()) : that.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = getUuid() != null ? getUuid().hashCode() : 0;
        result = 31 * result + (getToken() != null ? getToken().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }
}
