package org.taskmanager.report_server.dao.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.taskmanager.report_client.core.enums.ReportStatus;
import org.taskmanager.report_client.core.enums.ReportType;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    private UUID uuid;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReportStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ReportType type;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "file")
    private String file;
    @Type(JsonType.class)
    @Column(name = "param", nullable = false)
    private Map<String, Object> param;
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "create_date", precision = 3)
    private LocalDateTime createDate;
    @UpdateTimestamp(source = SourceType.DB)
    @Version
    @Column(name = "update_date", precision = 3)
    private LocalDateTime updateDate;

    public Report() {
    }

    public Report(UUID uuid, ReportStatus status, ReportType type, String description, String file, Map<String, Object> param) {
        this.uuid = uuid;
        this.status = status;
        this.type = type;
        this.description = description;
        this.file = file;
        this.param = param;
    }

    public Report(UUID uuid, ReportStatus status, ReportType type, String description, String file, Map<String, Object> param, LocalDateTime createDate, LocalDateTime updateDate) {
        this.uuid = uuid;
        this.status = status;
        this.type = type;
        this.description = description;
        this.file = file;
        this.param = param;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (getUuid() != null ? !getUuid().equals(report.getUuid()) : report.getUuid() != null) return false;
        if (getStatus() != report.getStatus()) return false;
        if (getType() != report.getType()) return false;
        if (getDescription() != null ? !getDescription().equals(report.getDescription()) : report.getDescription() != null)
            return false;
        if (getFile() != null ? !getFile().equals(report.getFile()) : report.getFile() != null) return false;
        if (getParam() != null ? !getParam().equals(report.getParam()) : report.getParam() != null) return false;
        if (getCreateDate() != null ? !getCreateDate().equals(report.getCreateDate()) : report.getCreateDate() != null)
            return false;
        return getUpdateDate() != null ? getUpdateDate().equals(report.getUpdateDate()) : report.getUpdateDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getUuid() != null ? getUuid().hashCode() : 0;
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getFile() != null ? getFile().hashCode() : 0);
        result = 31 * result + (getParam() != null ? getParam().hashCode() : 0);
        result = 31 * result + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
        result = 31 * result + (getUpdateDate() != null ? getUpdateDate().hashCode() : 0);
        return result;
    }
}
