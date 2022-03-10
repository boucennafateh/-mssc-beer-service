package org.fate7.msscbeerservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> handleExceptions(ConstraintViolationException e){
        List<String> errors = new ArrayList<>();
        e.getConstraintViolations().forEach(
                err -> errors.add(err.getPropertyPath() + " " + err.getMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
}
