package com.finalproject.testgenerator.controllers;

import com.finalproject.testgenerator.DTOs.AnswerDTO;
import com.finalproject.testgenerator.models.Answer;
import com.finalproject.testgenerator.services.AnswersService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/answers")
public class AnswersController {
    private final AnswersService answersService;

    private ModelMapper modelMapper;
    Logger logger = LoggerFactory.getLogger(SubjectsController.class);

    @Autowired
    public AnswersController (AnswersService answersService, ModelMapper modelMapper){
        this.answersService = answersService;
        this.modelMapper = modelMapper;
    }


    @ApiOperation(value = "View a list of available answers", response = List.class)
    @GetMapping
    public ResponseEntity<List<AnswerDTO>> getAnswers(){
        List<Answer> answers = answersService.getAllAnswers();
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for (Answer answer : answers){
            answerDTOS.add(this.convertToDto(answer));
        }

        return new ResponseEntity<>(answerDTOS, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "View an answer by id", response = Answer.class)
    @GetMapping("/{id}")
    public ResponseEntity<AnswerDTO> getAnswer(@PathVariable int id){
        Answer answer = answersService.getAnswerById(id);
        if (answer == null){
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        AnswerDTO answerDTO = this.convertToDto(answer);
        return new ResponseEntity<>(answerDTO, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add an answer")
    @PostMapping
    public ResponseEntity<AnswerDTO> createAnswer(@RequestBody AnswerDTO answer){
        answersService.createAnswer(this.convertToEntity(answer));
        return new ResponseEntity<>(answer, new HttpHeaders(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an answer")
    @PutMapping("/{id}")
    public ResponseEntity<AnswerDTO> updateAnswer(@PathVariable("id") int id,
                                               @RequestBody AnswerDTO answer) {
        Answer answer1 =  answersService.getAnswerById(id);

        if (answer1 == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (answer.getText() != null){
            answer1.setText(answer.getText());
        }
        if (answer.getVerdict() != -1){
            answer1.setVerdict(answer.getVerdict());
        }
        answersService.updateById(answer1);
        AnswerDTO answerDTO = this.convertToDto(answer1);
        return new ResponseEntity<>(answerDTO, HttpStatus.NO_CONTENT);
    }



    @ApiOperation(value = "Delete an answer bu id",
            response = String.class)
//    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID answer"),
//                            @ApiResponse(code = 404, message = "Answer not found"),
//                            @ApiResponse(code = 200, message = "Answer deleted")})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable int id) {
        Answer answer =  answersService.deleteById(id);

        if (answer == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    private AnswerDTO convertToDto(Answer answer) {
        AnswerDTO postDto = modelMapper.map(answer, AnswerDTO.class);
        return postDto;
    }

    private Answer convertToEntity(AnswerDTO postDto) {
        Answer answer = modelMapper.map(postDto, Answer.class);
        return answer;
    }

}






