package com.finalproject.testgenerator.models;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String text;

    @OneToOne
    private Subject subject;

    public Question(String text, Subject subject) {
        this.text = text;
        this.subject = subject;
    }

    public Question(String text) {
        this.text = text;
    }
    public Question() {
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Subject getSubject (){
        return this.subject;
    }
}
