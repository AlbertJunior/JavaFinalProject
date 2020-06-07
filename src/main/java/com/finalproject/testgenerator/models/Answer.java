package com.finalproject.testgenerator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@ApiModel(description = "All details about the Answer ")
@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn (name = "question_id")
    @JsonIgnore
    private Question question;

    public Answer(String text, int verdict) {
        this.text = text;
        this.verdict = verdict;
    }

}
