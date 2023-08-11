package org.taskmanager.task_client.core.dto.base;

import org.taskmanager.task_client.core.enums.TaskStatus;

import java.util.List;

public class FilterDTO {
    private List<TaskStatus> status;
    private List<ProjectRefDTO> project;
    private List<UserRefDTO> implementer;

    public FilterDTO() {
    }

    public FilterDTO(List<TaskStatus> status, List<ProjectRefDTO> project, List<UserRefDTO> implementer) {
        this.status = status;
        this.project = project;
        this.implementer = implementer;
    }

    public List<TaskStatus> getStatus() {
        return status;
    }

    public void setStatus(List<TaskStatus> status) {
        this.status = status;
    }

    public List<ProjectRefDTO> getProject() {
        return project;
    }

    public void setProject(List<ProjectRefDTO> project) {
        this.project = project;
    }

    public List<UserRefDTO> getImplementer() {
        return implementer;
    }

    public void setImplementer(List<UserRefDTO> implementer) {
        this.implementer = implementer;
    }
}
