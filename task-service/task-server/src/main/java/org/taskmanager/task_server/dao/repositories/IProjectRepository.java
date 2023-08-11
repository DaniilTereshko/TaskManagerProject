package org.taskmanager.task_server.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanager.task_client.core.enums.ProjectStatus;
import org.taskmanager.task_server.dao.entity.Project;

import java.util.List;
import java.util.UUID;

public interface IProjectRepository extends JpaRepository<Project, UUID> {
    Page<Project> findAllByStatus(PageRequest pageRequest, ProjectStatus status);
    List<Project> findAllByStaffContains(UUID uuid);
}
