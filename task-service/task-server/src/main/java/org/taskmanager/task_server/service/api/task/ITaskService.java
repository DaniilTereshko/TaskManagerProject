package org.taskmanager.task_server.service.api.task;

import org.springframework.data.domain.Page;
import org.taskmanager.task_client.core.dto.base.FilterDTO;
import org.taskmanager.task_client.core.dto.create.TaskCreateDTO;
import org.taskmanager.task_client.core.dto.update.PatchTaskStatusDTO;
import org.taskmanager.task_client.core.dto.update.TaskUpdateDTO;
import org.taskmanager.task_server.dao.entity.Task;

import java.util.Optional;
import java.util.UUID;

public interface ITaskService {
    Task save(TaskCreateDTO item);
    Page<Task> get(int page, int size, FilterDTO filterDTO);
    Task get(UUID uuid);
    Task update(TaskUpdateDTO item);
    Task updateStatus(PatchTaskStatusDTO item);
}
