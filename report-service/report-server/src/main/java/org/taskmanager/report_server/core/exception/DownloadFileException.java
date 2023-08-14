package org.taskmanager.report_server.core.exception;

public class DownloadFileException extends RuntimeException{
    public DownloadFileException() {
        super();
    }

    public DownloadFileException(String message) {
        super(message);
    }
}
