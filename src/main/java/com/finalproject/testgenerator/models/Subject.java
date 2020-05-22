package com.finalproject.testgenerator.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;


@ApiModel(description = "All details about the Subject ")
@Entity
public class Subject {

    @ApiModelProperty(notes = "The id of a Subject - unique")
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @ApiModelProperty(notes = "The name of a subject")
    @Column
    private String name;

    public Subject(){
    }

    public Subject(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
