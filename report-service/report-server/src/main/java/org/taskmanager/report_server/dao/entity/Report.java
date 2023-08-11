package org.taskmanager.report_server.dao.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.taskmanager.report_client.core.enums.ReportStatus;
import org.taskmanager.report_client.core.enums.ReportType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "report")
public class Report {
    @Id
    private UUID uuid;
    @Column(name = "status")
    private ReportStatus status;
    @Column(name = "type")
    private ReportType type;
    @Column(name = "description", nullable = false)
    private String description;
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "create_date", precision = 3)
    private LocalDateTime createDate;
    @UpdateTimestamp(source = SourceType.DB)
    @Version
    @Column(name = "update_date", precision = 3)
    private LocalDateTime updateDate;


}
