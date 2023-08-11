package org.taskmanager.task_server.dao.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.taskmanager.task_client.core.enums.ProjectStatus;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    private UUID uuid;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "manager", nullable = false)
    private UUID manager;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "projects_users", joinColumns = @JoinColumn(name = "id_project"))
    @Column(name = "id_user")
    private Set<UUID> staff = new HashSet<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProjectStatus status;
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "create_date", precision = 3)
    private LocalDateTime createDate;
    @UpdateTimestamp(source = SourceType.DB)
    @Version
    @Column(name = "update_date", precision = 3)
    private LocalDateTime updateDate;

    public Project() {
    }

    public Project(UUID uuid, String name, String description, UUID manager, Set<UUID> staff, ProjectStatus status, LocalDateTime createDate, LocalDateTime updateDate) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.staff = staff;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getManager() {
        return manager;
    }

    public void setManager(UUID manager) {
        this.manager = manager;
    }

    public Set<UUID> getStaff() {
        return staff;
    }

    public void setStaff(Set<UUID> staff) {
        this.staff = staff;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
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

    public void addUser(UUID uuid){
        this.staff.add(uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (getUuid() != null ? !getUuid().equals(project.getUuid()) : project.getUuid() != null) return false;
        if (getName() != null ? !getName().equals(project.getName()) : project.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(project.getDescription()) : project.getDescription() != null)
            return false;
        if (getManager() != null ? !getManager().equals(project.getManager()) : project.getManager() != null)
            return false;
        if (getStaff() != null ? !getStaff().equals(project.getStaff()) : project.getStaff() != null) return false;
        if (getStatus() != project.getStatus()) return false;
        if (getCreateDate() != null ? !getCreateDate().equals(project.getCreateDate()) : project.getCreateDate() != null)
            return false;
        return getUpdateDate() != null ? getUpdateDate().equals(project.getUpdateDate()) : project.getUpdateDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getUuid() != null ? getUuid().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getManager() != null ? getManager().hashCode() : 0);
        result = 31 * result + (getStaff() != null ? getStaff().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
        result = 31 * result + (getUpdateDate() != null ? getUpdateDate().hashCode() : 0);
        return result;
    }
}
