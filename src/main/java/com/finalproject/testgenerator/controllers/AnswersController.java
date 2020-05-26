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
    private final AnswersService service;

    @Autowired
    public AnswersController (AnswersService service){
        this.service = service;
    }


    @ApiOperation(value = "View a list of available answers", response = List.class)
    @GetMapping
    public ResponseEntity<List<Answer>> getAnswers(){
        List<Answer> answers = service.getAllAnswers();
        return new ResponseEntity<>(answers, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "View an answer by id", response = Answer.class)
    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswer(@PathVariable int id){
        Answer answer = service.getAnswerById(id);
        if (answer == null){
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(answer, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add an answer")
    @PostMapping
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer, @RequestHeader("my-number") int myNumber){
        Answer answer1 = service.createAnswer(answer);
        return new ResponseEntity<>(answer1, new HttpHeaders(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an answer")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAnswer(@PathVariable("id") int id,
                                               @RequestBody Answer answer) {
        Answer answer1 =  service.getAnswerById(id);

        if (answer1 == null) {
            return new ResponseEntity<>("Answer not found", HttpStatus.NOT_FOUND);
        }
        if (answer.getText() != null){
            answer1.setText(answer.getText());
        }
        if (answer.getVerdict() != -1){
            answer1.setVerdict(answer.getVerdict());
        }
        service.updateById(answer1);
        return new ResponseEntity<>("Answer updated", HttpStatus.NO_CONTENT);
    }



    @ApiOperation(value = "Delete an answer bu id",
            response = String.class)
//    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID answer"),
//                            @ApiResponse(code = 404, message = "Answer not found"),
//                            @ApiResponse(code = 200, message = "Answer deleted")})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable int id) {
        Answer answer =  service.deleteById(id);

        if (answer == null) {
            return new ResponseEntity<>("Answer not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Answer removed", HttpStatus.OK);
    }


}






