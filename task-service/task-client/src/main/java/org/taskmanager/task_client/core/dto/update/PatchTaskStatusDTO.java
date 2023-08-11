package org.taskmanager.task_client.core.dto.update;

import org.taskmanager.task_client.core.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class PatchTaskStatusDTO {
    private UUID uuid;
    private LocalDateTime updateDate;
    private TaskStatus status;

    public PatchTaskStatusDTO() {
    }

    public PatchTaskStatusDTO(UUID uuid, LocalDateTime updateDate, TaskStatus status) {
        this.uuid = uuid;
        this.updateDate = updateDate;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
