package org.taskmanager.audit_server.endpoints.web.exception_hendler.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.taskmanager.audit_server.endpoints.web.exception_hendler.exception.NoSuchAuditException;
import org.taskmanager.base_package.enums.ErrorType;
import org.taskmanager.base_package.errors.ErrorResponse;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler({
            RuntimeException.class
    })
    public ResponseEntity<ErrorResponse> handleInnerError(){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, "Внутренняя ошибка сервера. Попробуйте позже");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NoSuchAuditException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFountError(NoSuchAuditException exception){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, "Невозможно найти аудит с id: " + exception.getUuid());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
