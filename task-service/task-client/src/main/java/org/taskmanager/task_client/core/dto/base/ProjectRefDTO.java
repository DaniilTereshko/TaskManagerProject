package org.taskmanager.task_client.core.dto.base;

import java.util.UUID;

public class ProjectRefDTO {
    private UUID uuid;

    public ProjectRefDTO() {
    }

    public ProjectRefDTO(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
