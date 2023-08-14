package org.taskmanager.audit_server.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.taskmanager.audit_server.dao.entity.Audit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IAuditRepository extends JpaRepository<Audit, UUID>, JpaSpecificationExecutor<Audit> {
}
