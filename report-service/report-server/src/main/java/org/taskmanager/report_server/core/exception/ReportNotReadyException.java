package org.taskmanager.report_server.core.exception;

public class ReportNotReadyException extends RuntimeException{
    public ReportNotReadyException() {
    }

    public ReportNotReadyException(String message) {
        super(message);
    }
}
