package org.taskmanager.report_server.core.dto;

import org.springframework.web.multipart.MultipartFile;

public class Report {
    private MultipartFile file;

    public Report() {
    }

    public Report(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
