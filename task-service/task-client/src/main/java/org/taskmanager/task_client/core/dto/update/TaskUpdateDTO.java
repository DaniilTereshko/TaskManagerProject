package org.taskmanager.task_client.core.dto.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.taskmanager.task_client.core.dto.base.ProjectRefDTO;
import org.taskmanager.task_client.core.dto.base.UserRefDTO;
import org.taskmanager.task_client.core.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class TaskUpdateDTO {
    @NotNull(message = "Идентификатор задачи обязателен")
    private UUID uuid;
    @NotNull(message = "Указание проекта обязательно")
    private ProjectRefDTO project;
    @Size(max = 155, message = "Максимальный размер имени задачи 155 символов")
    private String title;
    private String description;
    private TaskStatus status;
    private UserRefDTO implementer;
    private LocalDateTime updateDate;

    public TaskUpdateDTO() {
    }

    public TaskUpdateDTO(UUID uuid, ProjectRefDTO project, String title, String description, TaskStatus status, UserRefDTO implementer, LocalDateTime updateDate) {
        this.uuid = uuid;
        this.project = project;
        this.title = title;
        this.description = description;
        this.status = status;
        this.implementer = implementer;
        this.updateDate = updateDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ProjectRefDTO getProject() {
        return project;
    }

    public void setProject(ProjectRefDTO project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public UserRefDTO getImplementer() {
        return implementer;
    }

    public void setImplementer(UserRefDTO implementer) {
        this.implementer = implementer;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
