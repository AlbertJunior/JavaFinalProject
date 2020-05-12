package com.finalproject.testgenerator.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private int verdic;

    public Answer(){

    }

    public Answer(String text, int verdic) {
        this.text = text;
        this.verdic = verdic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getVerdic() {
        return verdic;
    }

    public void setVerdic(int right) {
        this.verdic = right;
    }
}
