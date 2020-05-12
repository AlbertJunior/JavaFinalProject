package com.finalproject.testgenerator.controllers;

import com.finalproject.testgenerator.models.Answer;
import com.finalproject.testgenerator.services.AnswersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/answers")
public class AnswersController {

    @Autowired
    private AnswersService service;


    @ApiOperation(value = "View a list of available subjects", response = List.class)
    @GetMapping
    public ResponseEntity<List<Answer>> getAnswers(){
        List<Answer> answers = service.getAllAnswers();
        return new ResponseEntity<>(answers, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswer(@PathVariable int id){
        Answer answer = service.getAnswerById(id);
        if (answer == null){
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(answer, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a subject")
    @PostMapping
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer){
        Answer answer1 = service.createAnswer(answer);
        return new ResponseEntity<>(answer1, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAnswer(@PathVariable("id") int id, @RequestParam String text) {
        Answer answer =  service.updateById(id);

        if (answer == null) {
            return new ResponseEntity<>("Answer not found", HttpStatus.NOT_FOUND);
        }
        answer.setText(text);
        return new ResponseEntity<>("Answer updated", HttpStatus.NO_CONTENT);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateAnswer(@PathVariable("id") int id, @RequestParam Boolean right) {
//        Answer answer =  service.updateById(id);
//
//        if (answer == null) {
//            return new ResponseEntity<>("Answer not found", HttpStatus.NOT_FOUND);
//        }
//        answer.setRight(right);
//        return new ResponseEntity<>("Answer updated", HttpStatus.NO_CONTENT);
//    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable int id) {
        Answer answer =  service.deleteById(id);

        if (answer == null) {
            return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Subject removed", HttpStatus.OK);
    }


}
