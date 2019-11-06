package com.devaj.happens.controller;

import com.devaj.happens.exception.ApiError;
import com.devaj.happens.exception.InternalErrorException;
import com.devaj.happens.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ApiError> handleExceptionInternal(InternalErrorException ex){
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), new Date());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), defaultMessage, new Date());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}
