package org.taskmanager.report_server.core.dto;

import org.springframework.web.multipart.MultipartFile;

public class ReportFile {
    private MultipartFile file;

    public ReportFile() {
    }

    public ReportFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
