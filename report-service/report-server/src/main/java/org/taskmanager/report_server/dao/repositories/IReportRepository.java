package org.taskmanager.report_server.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmanager.report_server.dao.entity.Report;

import java.util.UUID;

public interface IReportRepository extends JpaRepository<Report, UUID> {
}
