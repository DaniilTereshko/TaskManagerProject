package org.taskmanager.task_server.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.taskmanager.task_client.core.dto.base.ProjectRefDTO;
import org.taskmanager.task_client.core.dto.base.TaskDTO;
import org.taskmanager.task_client.core.dto.base.UserRefDTO;
import org.taskmanager.task_server.dao.entity.Task;

import java.time.ZoneOffset;

public class TaskToTaskDTOConverter implements Converter<Task, TaskDTO> {
    @Override
    public TaskDTO convert(Task task) {
        return new TaskDTO(task.getUuid(), new ProjectRefDTO(task.getProject().getUuid()),
                task.getTitle(), task.getDescription(), task.getStatus(),
                new UserRefDTO(task.getImplementer()), task.getCreateDate().toInstant(ZoneOffset.UTC).toEpochMilli(),
                task.getUpdateDate().toInstant(ZoneOffset.UTC).toEpochMilli());
    }
}
