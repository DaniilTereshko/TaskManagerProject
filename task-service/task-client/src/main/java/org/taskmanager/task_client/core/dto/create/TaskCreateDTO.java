package org.taskmanager.task_client.core.dto.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.taskmanager.task_client.core.dto.base.ProjectRefDTO;
import org.taskmanager.task_client.core.dto.base.UserRefDTO;
import org.taskmanager.task_client.core.enums.TaskStatus;

public class TaskCreateDTO {
    @NotNull(message = "Указание проекта обязательно")
    private ProjectRefDTO project;
    @Size(max = 155, message = "Максимальный размер задачи проекта 155 символов")
    private String title;
    private String description;
    private TaskStatus status;
    private UserRefDTO implementer;

    public TaskCreateDTO() {
    }

    public TaskCreateDTO(ProjectRefDTO project, String title, String description, TaskStatus status, UserRefDTO implementer) {
        this.project = project;
        this.title = title;
        this.description = description;
        this.status = status;
        this.implementer = implementer;
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
}
