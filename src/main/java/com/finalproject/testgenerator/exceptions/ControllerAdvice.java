package com.finalproject.testgenerator.exceptions;

import com.finalproject.testgenerator.DTOs.NotFoundExceptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handleNotFoundExceptions(NotFoundException e) {
        NotFoundExceptionDTO errorDTO = new NotFoundExceptionDTO(e);
        logger.error(e.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

}


