package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class AnimalGlobalException {


    @ExceptionHandler
    public ResponseEntity<AnimalErrorResponse> handleException(AnimalException animalException) {
        AnimalErrorResponse animalErrorResponse = new AnimalErrorResponse(animalException.getStatus().value(),
                animalException.getMessage(), System.currentTimeMillis());
        log.error(animalException.getMessage());
        return new ResponseEntity<>(animalErrorResponse, animalException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<AnimalErrorResponse> handleException(Exception exception) {
        AnimalErrorResponse animalErrorResponse = new AnimalErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), System.currentTimeMillis());
        log.error(exception.getMessage());
        return new ResponseEntity<>(animalErrorResponse, HttpStatus.BAD_REQUEST);
    }
}