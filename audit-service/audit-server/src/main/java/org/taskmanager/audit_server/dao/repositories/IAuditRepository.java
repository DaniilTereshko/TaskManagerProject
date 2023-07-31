package org.taskmanager.audit_server.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanager.audit_server.dao.entity.Audit;

import java.util.Optional;
import java.util.UUID;

public interface IAuditRepository extends JpaRepository<Audit, UUID> {
    @Override
    <S extends Audit> S save(S entity);

    @Override
    Page<Audit> findAll(Pageable pageable);

    @Override
    Optional<Audit> findById(UUID uuid);
}
