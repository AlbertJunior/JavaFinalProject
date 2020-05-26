package com.finalproject.testgenerator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@ApiModel(description = "All details about the Subject ")
@Entity
@Getter
@Setter
public class Subject {

    @ApiModelProperty(notes = "The id of a Subject - unique")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ApiModelProperty(notes = "The name of a subject")
    @Column
    private String name;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions;


    public Subject(@NonNull String name) {
        this.name = name;
    }

    public Subject() {
    }


}
