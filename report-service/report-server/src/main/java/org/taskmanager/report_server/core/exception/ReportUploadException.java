package org.taskmanager.report_server.core.exception;

public class ReportUploadException extends RuntimeException{
    public ReportUploadException() {
        super();
    }

    public ReportUploadException(String message) {
        super(message);
    }
}
