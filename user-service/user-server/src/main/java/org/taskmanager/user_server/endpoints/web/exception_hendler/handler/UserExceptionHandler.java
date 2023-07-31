package org.taskmanager.user_server.endpoints.web.exception_hendler.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.taskmanager.base_package.enums.ErrorType;
import org.taskmanager.base_package.errors.ErrorResponse;
import org.taskmanager.base_package.errors.StructuredErrorResponse;
import org.taskmanager.user_server.endpoints.web.exception_hendler.exception.ConversionTypeException;
import org.taskmanager.user_server.endpoints.web.exception_hendler.exception.EmailAlreadyTakenException;
import org.taskmanager.user_server.endpoints.web.exception_hendler.exception.NoSuchUserException;
import org.taskmanager.user_server.endpoints.web.exception_hendler.exception.VersionsMatchException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StructuredErrorResponse> handleInvalidArgument(ConstraintViolationException exception){
        StructuredErrorResponse response = new StructuredErrorResponse(ErrorType.STRUCTURED_ERROR, new HashMap<>());
        Map<String, String> errors = response.getErrors();
        exception.getConstraintViolations()
                .forEach(v -> errors.put(v.getPropertyPath().toString(), v.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, "Запрос содержит некорректные данные. Измените запрос и отправьте его еще раз");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({
            IllegalArgumentException.class,
            IOException.class,
            IndexOutOfBoundsException.class,
            ArithmeticException.class,
            ConversionTypeException.class,
            Error.class
    })
    public ResponseEntity<ErrorResponse> handleInnerError(){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, "Внутренняя ошибка сервера. Попробуйте позже");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFountError(NoSuchUserException exception){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, "Невозможно найти пользователя с id: " + exception.getUuid());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<StructuredErrorResponse> handleEmailTakenError(EmailAlreadyTakenException exception){
        StructuredErrorResponse response = new StructuredErrorResponse(ErrorType.STRUCTURED_ERROR, new HashMap<>());
        response.getErrors().put("email", "Пользователь с такой почтой уже зарегистрирован: " + exception.getEmail());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(VersionsMatchException.class)
    public ResponseEntity<ErrorResponse> handleVersionsMathError(VersionsMatchException exception){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, "Текущая версия не совпадает с указанной");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
