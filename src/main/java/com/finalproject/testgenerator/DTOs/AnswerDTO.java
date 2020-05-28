package com.finalproject.testgenerator.DTOs;

public class AnswerDTO {
    private String text;
    private int verdict = -1;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getVerdict() {
        return verdict;
    }

    public void setVerdict(int verdict) {
        this.verdict = verdict;
    }
}
