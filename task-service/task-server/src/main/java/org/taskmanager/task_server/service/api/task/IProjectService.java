package org.taskmanager.task_server.service.api.task;

import org.springframework.data.domain.Page;
import org.taskmanager.task_client.core.dto.create.ProjectCreateDTO;
import org.taskmanager.task_client.core.dto.update.ProjectUpdateDTO;
import org.taskmanager.task_server.dao.entity.Project;

import java.util.List;
import java.util.UUID;

public interface IProjectService {
    Project save(ProjectCreateDTO item);
    Page<Project> get(int page, int size, boolean archived);
    Project get(UUID uuid);
    Project update(ProjectUpdateDTO item);

    List<Project> findAllByUser(UUID uuid);
}
