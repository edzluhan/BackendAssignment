package com.origin.backendassignment.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.origin.backendassignment.response.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleValidationErrors(ConstraintViolationException ex) {
        return new ErrorResponse(ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()));
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ErrorResponse(ex.getFieldErrors().stream()
                .map(fieldError -> String.format("%s", fieldError.getDefaultMessage()))
                .collect(Collectors.toList()));
    }

    @ResponseBody
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleInvalidFormatException(InvalidFormatException ex) {
        String message = ex.getMessage();

        if (message.contains("OwnershipStatus")) {
            message = String.format("'%s' is not one of the values accepted for ownership_status: [mortgaged, owned]", ex.getValue());
        }

        if (message.contains("MaritalStatus")) {
            message = String.format("'%s' is not one of the values accepted for marital_status: [single, married]", ex.getValue());
        }

        return new ErrorResponse(List.of(message));
    }

}
