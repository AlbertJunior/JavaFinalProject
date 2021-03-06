package com.finalproject.testgenerator.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@ApiModel(description = "Details about users")
@Entity (name = "Account") //User does't work for postgres database - heroku
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

}