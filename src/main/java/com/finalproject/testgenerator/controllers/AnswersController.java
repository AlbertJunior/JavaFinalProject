package com.finalproject.testgenerator.controllers;

import com.finalproject.testgenerator.DTOs.AnswerDTO;
import com.finalproject.testgenerator.exceptions.NotFoundException;
import com.finalproject.testgenerator.models.Answer;
import com.finalproject.testgenerator.services.AnswersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * This class gets all the requests for answers and takes care of them
 */
@RestController
@Api(value = "Answer Management System")
@RequestMapping("api/v1/answers")
public class AnswersController {
    private final AnswersService answersService;

    private ModelMapper modelMapper;
    private Logger logger = LoggerFactory.getLogger(AnswersController.class);

    @Autowired
    public AnswersController (AnswersService answersService, ModelMapper modelMapper){
        this.answersService = answersService;
        this.modelMapper = modelMapper;
    }


    @ApiOperation(value = "View a list of available answers", response = List.class)
    @GetMapping
    @ResponseStatus (value = HttpStatus.OK)
    public List<AnswerDTO> getAnswers(){
        List<Answer> answers = answersService.getAllAnswers();
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for (Answer answer : answers){
            answerDTOS.add(modelMapper.map(answer, AnswerDTO.class));
        }
        logger.info("Get all the answers");
        return answerDTOS;
    }

    @ApiOperation(value = "View an answer by id", response = AnswerDTO.class)
    @GetMapping("/{id}")
    @ResponseStatus (value = HttpStatus.OK)
    public AnswerDTO getAnswer(@PathVariable int id) throws NotFoundException {
        Answer answer = answersService.getAnswerById(id);
        if (answer == null){
            logger.warn("Answer with id " + id + " not found");
            throw new NotFoundException("Answer with id " + id + " not found");
        }
        AnswerDTO answerDTO = modelMapper.map(answer, AnswerDTO.class);
        logger.info("Get an answer by id" + id);
        return answerDTO;
    }

    @ApiOperation(value = "Add an answer", response = AnswerDTO.class)
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AnswerDTO createAnswer(@RequestBody AnswerDTO answer,
                                  @RequestHeader(name = "Authorization") String token){
        Answer answer1 = modelMapper.map(answer, Answer.class);
        answersService.createAnswer(answer1);
        logger.info("Answer " + answer1.getId() + " was created");
        return answer;
    }

    @ApiOperation(value = "Update an answer", response = AnswerDTO.class)
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public AnswerDTO updateAnswer(@PathVariable("id") int id,
                                  @RequestBody AnswerDTO answer,
                                  @RequestHeader(name = "Authorization") String token) throws NotFoundException {
        Answer answer1 =  answersService.getAnswerById(id);

        if (answer1 == null) {
            throw new NotFoundException("Answer " + id + " not found");
        }
        if (answer.getText() != null){
            answer1.setText(answer.getText());
        }
        if (answer.getVerdict() != -1){
            answer1.setVerdict(answer.getVerdict());
        }
        answersService.updateById(answer1);
        logger.info("Answer " + answer1.getId() + " was updated");
        AnswerDTO answerDTO = modelMapper.map(answer, AnswerDTO.class);
        return answerDTO;
    }



    @ApiOperation(value = "Delete an answer by id")
//    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID answer"),
//                            @ApiResponse(code = 404, message = "Answer not found"),
//                            @ApiResponse(code = 200, message = "Answer deleted")})
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteAnswer(@PathVariable int id,
                                          @RequestHeader(name = "Authorization") String token) throws NotFoundException {
        Answer answer =  answersService.deleteById(id);

        if (answer == null) {
            logger.info("Answer " + id + " was not found");
            throw new NotFoundException("Answer " + id + " was not found");
        }
        logger.info("Answer " + id + " was deleted");
        return null;
    }



}






