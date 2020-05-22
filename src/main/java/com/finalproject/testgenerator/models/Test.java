package com.finalproject.testgenerator.models;

import com.finalproject.testgenerator.algorithms.Algorithm;
import com.finalproject.testgenerator.algorithms.DynamicProgrammingAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test {

    private List<Question> questions;
    private Subject subject;
    private int totalTime;
    private int difficulty;

    public Test (int totalTime, Subject subject, List<Question> questionsList){
        this.totalTime = totalTime;
        this.subject = subject;
        this.questions = new ArrayList<>();

        Predicate<Question> bySubject = question -> question.getSubject() == subject;

        initTest(questionsList.stream().filter(bySubject)
                .collect(Collectors.toList())) ;
    }

    private void initTest(List<Question> questionsList) {
        Algorithm dynamicAlgorithm = new DynamicProgrammingAlgorithm();
        dynamicAlgorithm.resolver(this, questionsList);
        calculateDifficulty();
        calculateTotalTime();
    }

    private void calculateTotalTime() {
        this.totalTime = 0;
        for (Question question: questions){
            this.totalTime += question.getTimeInSeconds();
        }
    }

    private void calculateDifficulty() {
        this.difficulty = 0;
        for (Question question: questions){
            this.difficulty += question.getDifficulty();
        }
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

}
