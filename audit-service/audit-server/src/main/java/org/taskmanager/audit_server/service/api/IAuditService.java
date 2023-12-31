package org.taskmanager.audit_server.service.api;

import org.springframework.data.domain.Page;
import org.taskmanager.audit_client.core.dto.create.AuditCreateDTO;
import org.taskmanager.audit_server.dao.entity.Audit;
import org.taskmanager.report_client.core.dto.ReportParamAuditDTO;

import java.util.List;
import java.util.UUID;

public interface IAuditService {
    Audit save(AuditCreateDTO item);
    Page<Audit> get(int page, int size);
    Audit get(UUID uuid);
    List<Audit> getAllByParam(ReportParamAuditDTO param);
}
