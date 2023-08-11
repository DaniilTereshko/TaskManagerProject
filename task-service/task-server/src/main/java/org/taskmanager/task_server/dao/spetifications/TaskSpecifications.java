package org.taskmanager.task_server.dao.spetifications;

import org.springframework.data.jpa.domain.Specification;
import org.taskmanager.task_client.core.dto.base.ProjectRefDTO;
import org.taskmanager.task_client.core.dto.base.UserRefDTO;
import org.taskmanager.task_client.core.enums.TaskStatus;
import org.taskmanager.task_server.dao.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskSpecifications {
    public static Specification<Task> byStatus(List<TaskStatus> status) {
        return (root, query, builder) ->
                builder.in(root.get("status")).value(status);
    }
    public static Specification<Task> byImplementer(List<UserRefDTO> implementer) {
        List<UUID> uuids = implementer.stream().map(UserRefDTO::getUuid).toList();
        return (root, query, builder) ->
                builder.in(root.get("implementer")).value(uuids);
    }
    public static Specification<Task> byProject(List<ProjectRefDTO> project) {
        List<UUID> uuids = project.stream().map(ProjectRefDTO::getUuid).toList();
        return (root, query, builder) ->
                builder.in(root.get("project").get("uuid")).value(uuids);
    }
}
