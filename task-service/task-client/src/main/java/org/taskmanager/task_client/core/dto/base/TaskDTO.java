package org.taskmanager.task_client.core.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.taskmanager.task_client.core.enums.TaskStatus;

import java.util.UUID;

public class TaskDTO {
    private UUID uuid;
    private ProjectRefDTO project;
    private String title;
    private String description;
    private TaskStatus status;
    private UserRefDTO implementer;
    @JsonProperty("dt_create")
    private Long createDate;
    @JsonProperty("dt_update")
    private Long updateDate;

    public TaskDTO() {
    }

    public TaskDTO(UUID uuid, ProjectRefDTO project, String title, String description, TaskStatus status, UserRefDTO implementer, Long createDate, Long updateDate) {
        this.uuid = uuid;
        this.project = project;
        this.title = title;
        this.description = description;
        this.status = status;
        this.implementer = implementer;
        this.createDate = createDate;
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

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
