package com.finalproject.testgenerator.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@ApiModel(description = "All details about the Subject ")
@Entity
public class Subject {

    @ApiModelProperty(notes = "The database generated employee ID")
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Subject(){
    }

    public Subject(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
