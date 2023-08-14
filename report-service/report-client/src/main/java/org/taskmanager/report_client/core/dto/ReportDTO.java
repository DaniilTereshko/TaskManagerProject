package org.taskmanager.report_client.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.taskmanager.report_client.core.enums.ReportStatus;
import org.taskmanager.report_client.core.enums.ReportType;

import java.util.Map;
import java.util.UUID;

public class ReportDTO {
    private UUID uuid;
    private ReportStatus status;
    private ReportType type;
    private String description;
    private Map<String, Object> params;
    @JsonProperty("dt_create")
    private Long createDate;
    @JsonProperty("dt_update")
    private Long updateDate;

    public ReportDTO() {
    }

    public ReportDTO(UUID uuid, ReportStatus status, ReportType type, String description, Map<String, Object> params, Long createDate, Long updateDate) {
        this.uuid = uuid;
        this.status = status;
        this.type = type;
        this.description = description;
        this.params = params;
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

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
