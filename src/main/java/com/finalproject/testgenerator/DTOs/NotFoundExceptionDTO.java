package com.finalproject.testgenerator.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundExceptionDTO {
    private String message;
    private String exceptionType;

    public NotFoundExceptionDTO() {}

    public NotFoundExceptionDTO(Exception e) {
        this.message = e.getMessage();
        this.exceptionType = e.getClass().getName();
    }
}
