package org.taskmanager.report_server.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanager.report_server.dao.entity.AuditParam;

import java.util.UUID;

public interface IAuditParamRepository extends JpaRepository<AuditParam, UUID> {
}
