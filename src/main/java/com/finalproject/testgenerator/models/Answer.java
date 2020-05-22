package com.finalproject.testgenerator.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String text;
    private int verdict;

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
