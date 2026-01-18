package com.gayathri.foody.controller;

import com.gayathri.foody.exception.InvalidIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FoodItemExceptionHandler {

    @ExceptionHandler(InvalidIdException.class)
    ResponseEntity<String> handleInvalidIdException(InvalidIdException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
