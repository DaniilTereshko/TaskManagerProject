package org.taskmanager.task_server.dao.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.taskmanager.task_client.core.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "project", nullable = false)
    private Project project;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;
    @Column(name = "implementer")
    private UUID implementer;
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "create_date", precision = 3)
    private LocalDateTime createDate;
    @UpdateTimestamp(source = SourceType.DB)
    @Version
    @Column(name = "update_date", precision = 3)
    private LocalDateTime updateDate;

    public Task() {
    }

    public Task(UUID uuid, Project project, String title, String description, TaskStatus status, UUID implementer, LocalDateTime createDate, LocalDateTime updateDate) {
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
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

    public UUID getImplementer() {
        return implementer;
    }

    public void setImplementer(UUID implementer) {
        this.implementer = implementer;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (getUuid() != null ? !getUuid().equals(task.getUuid()) : task.getUuid() != null) return false;
        if (getProject() != null ? !getProject().equals(task.getProject()) : task.getProject() != null) return false;
        if (getTitle() != null ? !getTitle().equals(task.getTitle()) : task.getTitle() != null) return false;
        if (getDescription() != null ? !getDescription().equals(task.getDescription()) : task.getDescription() != null)
            return false;
        if (getStatus() != task.getStatus()) return false;
        if (getImplementer() != null ? !getImplementer().equals(task.getImplementer()) : task.getImplementer() != null)
            return false;
        if (getCreateDate() != null ? !getCreateDate().equals(task.getCreateDate()) : task.getCreateDate() != null)
            return false;
        return getUpdateDate() != null ? getUpdateDate().equals(task.getUpdateDate()) : task.getUpdateDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getUuid() != null ? getUuid().hashCode() : 0;
        result = 31 * result + (getProject() != null ? getProject().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getImplementer() != null ? getImplementer().hashCode() : 0);
        result = 31 * result + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
        result = 31 * result + (getUpdateDate() != null ? getUpdateDate().hashCode() : 0);
        return result;
    }
}
