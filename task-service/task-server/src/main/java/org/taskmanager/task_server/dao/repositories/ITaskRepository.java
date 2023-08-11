package org.taskmanager.task_server.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.taskmanager.task_server.dao.entity.Task;

import java.util.UUID;

public interface ITaskRepository extends JpaRepository<Task, UUID>, JpaSpecificationExecutor<Task> {
}
