package com.finalproject.testgenerator.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

//https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application

/**
 * This class hides the id and the subject for a question
 */
@Getter
@Setter
public class QuestionDTO {

    private String text;
    private List<AnswerDTO> answers;
    private int difficulty = -1;
    private int timeInSeconds;

}
