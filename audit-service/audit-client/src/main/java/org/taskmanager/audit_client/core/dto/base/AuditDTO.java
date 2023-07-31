package org.taskmanager.audit_client.core.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.taskmanager.audit_client.core.enums.EssenceType;

import java.time.LocalDateTime;
import java.util.UUID;

public class AuditDTO {
    private UUID uuid;
    private UserDTO user;
    private String text;
    private EssenceType type;
    private String id;
    @JsonProperty("dt_create")
    private LocalDateTime createDate;

    public AuditDTO() {
    }

    public AuditDTO(UUID uuid, UserDTO user, String text, EssenceType type, String id, LocalDateTime createDate) {
        this.uuid = uuid;
        this.user = user;
        this.text = text;
        this.type = type;
        this.id = id;
        this.createDate = createDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
