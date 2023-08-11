package org.taskmanager.task_client.core.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.taskmanager.task_client.core.enums.ProjectStatus;

import java.util.Set;
import java.util.UUID;

public class ProjectDTO {
    private UUID uuid;
    private String name;
    private String description;
    private UserRefDTO manager;
    private Set<UserRefDTO> staff;
    private ProjectStatus status;
    @JsonProperty("dt_create")
    private Long createDate;
    @JsonProperty("dt_update")
    private Long updateDate;

    public ProjectDTO() {
    }

    public ProjectDTO(UUID uuid, String name, String description, UserRefDTO manager, Set<UserRefDTO> staff, ProjectStatus status, Long createDate, Long updateDate) {
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

    public UserRefDTO getManager() {
        return manager;
    }

    public void setManager(UserRefDTO manager) {
        this.manager = manager;
    }

    public Set<UserRefDTO> getStaff() {
        return staff;
    }

    public void setStaff(Set<UserRefDTO> staff) {
        this.staff = staff;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
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
