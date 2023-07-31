package org.taskmanager.audit_server.dao.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.taskmanager.audit_client.core.enums.EssenceType;
import org.taskmanager.user_client.core.enums.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit")
public class Audit {
    @Id
    private UUID uuid;
    @Column(name = "text", nullable = false)
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EssenceType type;
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @Column(name = "mail", nullable = false)
    private String mail;
    @Column(name = "fio", nullable = false)
    private String fio;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "create_date", precision = 3)
    private LocalDateTime createDate;

    public Audit() {
    }

    public Audit(String text, EssenceType type, String id, UUID userId, String mail, String fio, UserRole role) {
        this.text = text;
        this.type = type;
        this.id = id;
        this.userId = userId;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
    }

    public Audit(UUID uuid, String text, EssenceType type, String id, UUID userId, String mail, String fio, UserRole role, LocalDateTime createDate) {
        this.uuid = uuid;
        this.text = text;
        this.type = type;
        this.id = id;
        this.userId = userId;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.createDate = createDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EssenceType getType() {
        return type;
    }

    public void setType(EssenceType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
