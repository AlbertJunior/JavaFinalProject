package com.finalproject.testgenerator.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@ApiModel(description = "All details about the Question")
@Entity
public class Question {
    @ApiModelProperty(notes = "The id of an Answer - unique")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ApiModelProperty(notes = "The content of a question")
    @Column
    private String text;

    @ApiModelProperty(notes = "The subject it belongs to")
    @OneToOne
    private Subject subject;

    @ApiModelProperty(notes = "All the answers for this question")
    @OneToMany
    private List<Answer> answers;

    @ApiModelProperty(notes = "The difficulty of a question from 1 to 100, -1 is for unset")
    @Column
    private int difficulty = -1;

    @ApiModelProperty(notes = "The time to answer this question")
    @Column
    private int timeInSeconds;

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
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

    public Question(String text, Subject subject) {
        this.text = text;
        this.subject = subject;
    }

    public Question(String text) {
        this.text = text;
    }
    public Question(String text, int timeInSeconds, int difficulty, Subject subject) {
        this.text = text;
        this.difficulty = difficulty;
        this.timeInSeconds = timeInSeconds;
        this.subject = subject;
    }
    public Question() {
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Subject getSubject (){
        return this.subject;
    }
}
