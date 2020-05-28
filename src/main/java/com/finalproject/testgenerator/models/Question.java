package com.finalproject.testgenerator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@ApiModel(description = "All details about the Question")
@Entity
@Getter
@Setter
public class Question {
    @ApiModelProperty(notes = "The id of an Answer - unique")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ApiModelProperty(notes = "The content of a question")
    @Column
    private String text;

    @ApiModelProperty(notes = "The subject it belongs to")
    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonIgnore
    private Subject subject;

    @ApiModelProperty(notes = "All the answers for this question")
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;

    @ApiModelProperty(notes = "The difficulty of a question from 1 to 100, -1 is for unset")
    @Column
    private int difficulty = -1;

    @ApiModelProperty(notes = "The time to answer this question")
    @Column
    private int timeInSeconds;


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

}
