package com.finalproject.testgenerator.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {
    private String text;
    private int verdict = -1;
}
