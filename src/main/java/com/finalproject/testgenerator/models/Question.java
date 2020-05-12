package com.finalproject.testgenerator.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String text;

    public Question(String text) {
        this.text = text;
    }
}
