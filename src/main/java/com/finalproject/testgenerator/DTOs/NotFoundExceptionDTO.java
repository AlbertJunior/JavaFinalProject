package com.finalproject.testgenerator.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the response for catching the NotFoundException
 */
@Getter
@Setter
@NoArgsConstructor
public class NotFoundExceptionDTO {
    private String message;
    private String exceptionType;

    public NotFoundExceptionDTO(Exception e) {
        this.message = e.getMessage();
        this.exceptionType = e.getClass().getName();
    }
}
