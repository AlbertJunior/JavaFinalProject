package com.finalproject.testgenerator.controllers;

import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.repositories.QuestionsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionsController {

    private QuestionsRepository repository;

    @GetMapping("/")
    String hello(){
        return "Hello wordl!";
    }

    @GetMapping("/questions")
    Iterable<Question> questions(){
        return repository.findAll();
    }

    QuestionsController(QuestionsRepository repository){
        this.repository = repository;
    }
}
