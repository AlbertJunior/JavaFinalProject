package com.finalproject.testgenerator.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(description = "All details about the Answer ")
@Entity
public class Answer {

    @ApiModelProperty(notes = "The id of an Answer - unique")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ApiModelProperty(notes = "The content of an answer")
    @Column
    private String text;

    @ApiModelProperty(notes = "1 - the answer is right, -1 - unset, 0 - otherwise")
    @Column
    private int verdict = -1;

    public Answer(){
    }

    public Answer(String text, int verdict) {
        this.text = text;
        this.verdict = verdict;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getVerdict() {
        return verdict;
    }

    public void setVerdict(int right) {
        this.verdict = right;
    }
}
