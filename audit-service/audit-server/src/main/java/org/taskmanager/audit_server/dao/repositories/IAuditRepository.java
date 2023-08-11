package org.taskmanager.audit_server.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanager.audit_server.dao.entity.Audit;

import java.util.UUID;

public interface IAuditRepository extends JpaRepository<Audit, UUID> {
}
