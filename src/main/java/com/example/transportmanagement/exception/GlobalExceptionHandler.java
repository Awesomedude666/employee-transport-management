package com.example.transportmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleResourceNotFound(
            ResourceNotFoundException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());

        return response;
    }

    // this is inbuilt exception, we don't need to write as a custom exception.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return errors;
    }

    @ExceptionHandler(BookingConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleBookingConflict(BookingConflictException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());

        return response;
    }
}