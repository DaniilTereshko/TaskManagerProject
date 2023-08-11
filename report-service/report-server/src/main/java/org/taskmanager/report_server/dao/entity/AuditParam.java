package org.taskmanager.report_server.dao.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "audit_param")
public class AuditParam {
    @Id
    private UUID uuid;
    @Column(name = "user", nullable = false)
    private UUID user;
    @Column(name = "from", precision = 3, nullable = false)
    private LocalDate from;
    @Column(name = "to", precision = 3, nullable = false)
    private LocalDate to;
    @OneToOne
    @JoinColumn(name = "report", nullable = false)
    private Report report;

    public AuditParam() {
    }

    public AuditParam(UUID uuid, UUID user, LocalDate from, LocalDate to, Report report) {
        this.uuid = uuid;
        this.user = user;
        this.from = from;
        this.to = to;
        this.report = report;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditParam that = (AuditParam) o;

        if (getUuid() != null ? !getUuid().equals(that.getUuid()) : that.getUuid() != null) return false;
        if (getUser() != null ? !getUser().equals(that.getUser()) : that.getUser() != null) return false;
        if (getFrom() != null ? !getFrom().equals(that.getFrom()) : that.getFrom() != null) return false;
        if (getTo() != null ? !getTo().equals(that.getTo()) : that.getTo() != null) return false;
        return getReport() != null ? getReport().equals(that.getReport()) : that.getReport() == null;
    }

    @Override
    public int hashCode() {
        int result = getUuid() != null ? getUuid().hashCode() : 0;
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getFrom() != null ? getFrom().hashCode() : 0);
        result = 31 * result + (getTo() != null ? getTo().hashCode() : 0);
        result = 31 * result + (getReport() != null ? getReport().hashCode() : 0);
        return result;
    }
}
