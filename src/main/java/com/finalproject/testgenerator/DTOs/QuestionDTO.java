package com.finalproject.testgenerator.DTOs;

import java.util.List;

//https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
public class QuestionDTO {

    private String text;
    private List<AnswerDTO> answers;
    private int difficulty = -1;
    private int timeInSeconds;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

}
