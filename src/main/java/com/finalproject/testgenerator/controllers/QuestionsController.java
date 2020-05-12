package com.finalproject.testgenerator.controllers;

import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.repositories.QuestionsRepository;
import com.finalproject.testgenerator.services.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
public class QuestionsController {

    @Autowired
    private QuestionsService service;

    @GetMapping("/hello")
    String hello(){
        return "Hello wordl!";
    }

    @GetMapping
    public ResponseEntity<List<Question>> getQuestions(){
        List<Question> questions = service.getAllQuestions();
        return new ResponseEntity<>(questions, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question){
        Question question1 = service.createQuestion(question);
        return new ResponseEntity<>(question1, new HttpHeaders(), HttpStatus.CREATED);
    }
}
