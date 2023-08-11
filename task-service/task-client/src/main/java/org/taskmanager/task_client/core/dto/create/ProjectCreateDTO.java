package org.taskmanager.task_client.core.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.taskmanager.task_client.core.dto.base.UserRefDTO;
import org.taskmanager.task_client.core.enums.ProjectStatus;

import java.util.Set;

public class ProjectCreateDTO {
    @NotBlank(message = "Имя проекта обязательно")
    @Size(max = 155, message = "Максимальный размер имени проекта 155 символов")
    private String name;
    private String description;
    @NotNull(message = "Указание менеджера проекта обязательно")
    private UserRefDTO manager;
    private Set<UserRefDTO> staff;
    @NotNull(message = "Статус проекта обязателен")
    private ProjectStatus status;

    public ProjectCreateDTO() {
    }

    public ProjectCreateDTO(String name, String description, UserRefDTO manager, Set<UserRefDTO> staff, ProjectStatus status) {
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.staff = staff;
        this.status = status;
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
}
