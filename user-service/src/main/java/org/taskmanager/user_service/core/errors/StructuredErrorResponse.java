package org.taskmanager.user_service.core.errors;

import org.taskmanager.user_service.core.enums.ErrorType;

import java.util.List;
import java.util.Map;

public class StructuredErrorResponse {
    private ErrorType logref;
    private Map<String, String> errors;

    public StructuredErrorResponse() {
    }

    public StructuredErrorResponse(ErrorType logref, Map<String, String> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public ErrorType getLogref() {
        return logref;
    }

    public void setLogref(ErrorType logref) {
        this.logref = logref;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
