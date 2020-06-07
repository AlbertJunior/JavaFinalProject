package com.finalproject.testgenerator.models;

import com.finalproject.testgenerator.algorithms.Algorithm;
import com.finalproject.testgenerator.algorithms.DynamicProgrammingAlgorithm;
import com.finalproject.testgenerator.exceptions.NotFoundException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class represents a test that we can get from api/v1/tests API
 */
@Getter
@Setter
public class Test {

    private List<Question> questions;
    private Subject subject;
    private int totalTime;
    private int difficulty;

    /**
     * For every request, we create a new test and return it
     * @param totalTime
     * @param subject
     * @param questionsList
     * @throws NotFoundException
     */
    public Test (int totalTime, Subject subject, List<Question> questionsList) throws NotFoundException {
        this.totalTime = totalTime;
        this.subject = subject;
        this.questions = new ArrayList<>();

        Predicate<Question> bySubject = question -> question.getSubject().getId() == subject.getId();

        initTest(questionsList.stream().filter(bySubject)
                .collect(Collectors.toList())) ;
    }

    /**
     * For a list of questions and a specific time, this method creates an optimum test
     * It uses an Algorithm
     * @param questionsList
     * @throws NotFoundException
     */
    private void initTest(List<Question> questionsList) throws NotFoundException {
        Algorithm dynamicAlgorithm = new DynamicProgrammingAlgorithm();
        dynamicAlgorithm.resolver(this, questionsList);
        calculateDifficulty();
        calculateTotalTime();
    }

    /**
     * A test can be shorter then the total time a client requested
     */
    private void calculateTotalTime() {
        this.totalTime = 0;
        for (Question question: questions){
            this.totalTime += question.getTimeInSeconds();
        }
    }

    /**
     * This method calculates the optimum difficulty for parameters requested
     */
    private void calculateDifficulty() {
        this.difficulty = 0;
        for (Question question: questions){
            this.difficulty += question.getDifficulty();
        }
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

}
