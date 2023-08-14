package org.taskmanager.audit_server.endpoints.web.exception_hendler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.taskmanager.audit_server.core.exception.IncorrectDataException;
import org.taskmanager.audit_server.core.exception.NotFoundException;
import org.taskmanager.base_package.enums.ErrorType;
import org.taskmanager.base_package.errors.ErrorResponse;

//@RestControllerAdvice
public class AuditExceptionHandler {
    private static final String NOT_READABLE = "Запрос содержит некорректные данные. Измените запрос и отправьте его еще раз";
    private static final String INTERNAL_SERVER_ERROR = "Внутренняя ошибка сервера. Сервер не смог корректно обработать запрос";

    @ExceptionHandler({RuntimeException.class, Error.class})
    public ResponseEntity<ErrorResponse> handleInnerError() {
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, NOT_READABLE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundError(NotFoundException exception) {
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectDataError(NotFoundException exception) {
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
