package org.taskmanager.audit_client.core.dto.creare;

import org.taskmanager.audit_client.core.enums.EssenceType;
import org.taskmanager.user_client.core.enums.UserRole;

import java.util.UUID;

public class AuditCreateDTO {
    private UUID uuid;
    private String mail;
    private String fio;
    private UserRole role;
    private String text;
    private EssenceType type;
    private String id;

    public AuditCreateDTO() {
    }

    public AuditCreateDTO(UUID uuid, String mail, String fio, UserRole role, String text, EssenceType type, String id) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.text = text;
        this.type = type;
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
}
