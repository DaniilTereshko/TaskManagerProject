package org.taskmanager.task_client.core.dto.base;

import java.util.UUID;

public class UserRefDTO {
    private UUID uuid;

    public UserRefDTO() {
    }

    public UserRefDTO(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
