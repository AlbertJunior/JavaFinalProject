package com.finalproject.testgenerator.controllers;

import com.finalproject.testgenerator.DTOs.AnswerDTO;
import com.finalproject.testgenerator.DTOs.QuestionDTO;
import com.finalproject.testgenerator.exceptions.NotFoundException;
import com.finalproject.testgenerator.models.Answer;
import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.models.Subject;
import com.finalproject.testgenerator.services.SubjectsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class gets all the requests for subjects and questions and takes care of them
 */
@RestController
@RequestMapping("api/v1/subjects")
@Api(value = "Subject Management System")
@Transactional
public class SubjectsController {

    private final SubjectsService subjectService;
    private ModelMapper modelMapper;
    private Logger logger = LoggerFactory.getLogger(SubjectsController.class);

    @Autowired
    public SubjectsController(SubjectsService subjectService, ModelMapper modelMapper) {
        this.subjectService = subjectService;
        this.modelMapper = modelMapper;
    }


    @ApiOperation(value = "View a list of available subjects", response = List.class)
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Subject> getSubjects() {
        List<Subject> questions = subjectService.getAllSubjects();
        logger.info("Get all the subjects");
        return questions;
    }

    @ApiOperation(value = "View a subject with a specific id", response = Subject.class)
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Subject getSubject(@PathVariable int id) throws NotFoundException {
        Subject subject = subjectService.getSubjectById(id);
        if (subject == null) {
            logger.warn("Subject " + id + " not found");
            throw new NotFoundException("Subject " + id + " not found");
        }
        logger.info("Get a subject by id");
        return subject;
    }

    @ApiOperation(value = "Add a subject", response = Subject.class)
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Subject createSubject(@RequestBody Subject subject,
                                 @RequestHeader(name = "Authorization") String token) {
        Subject subject1 = subjectService.createSubject(subject);
        logger.info("The subject with the id " +  subject1.getId() + " was created");
        return subject1;
    }

    @ApiOperation(value = "Update a subject", response = Subject.class)
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Subject updateSubject(@PathVariable("id") int id,
                                 @RequestBody Subject subject,
                                 @RequestHeader(name = "Authorization") String token) throws NotFoundException {
        Subject subject1 = subjectService.getSubjectById(id);

        if (subject1 == null) {
            logger.warn("Subject not found");
            throw new NotFoundException("Subject " + id + " not found");
        }
        if (subject.getName() != null) {
            subject1.setName(subject.getName());
        }
        logger.info("The subject with the id " +  subject1.getId() + " was updated");
        return subjectService.updateById(subject1);
    }

    @ApiOperation(value = "Delete a subject")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteSubject(@PathVariable int id,
                                                @RequestHeader(name = "Authorization") String token) throws NotFoundException {
        Subject subject = subjectService.deleteById(id);

        if (subject == null) {
            logger.warn("Subject not found");
            throw new NotFoundException("Subject " + id + " not found");
        }
        logger.info("The subject with the id " +  subject.getId() + " was deleted");
        return null;
    }


//    -------------------------------------- QUESTIONS --------------------------------------------------------------------------

    @GetMapping(value = "/{id}/questions")
    @ResponseStatus(value = HttpStatus.OK)
    public List<QuestionDTO> getQuestions(@PathVariable int id) {
        Subject subject = subjectService.getSubjectById(id);
        logger.info("Get all the questions for subject " + id);

        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : subject.getQuestions()){
            questionDTOS.add(modelMapper.map(question, QuestionDTO.class));
        }
        return questionDTOS;
    }

    @GetMapping(value = "/{id}/questions/{id1}")
    @ResponseStatus(value = HttpStatus.OK)
    public QuestionDTO getQuestion(@PathVariable int id, @PathVariable int id1) throws NotFoundException {
        Subject subject = subjectService.getSubjectById(id);
        for (Question question : subject.getQuestions()){
            if (question.getId() == id1) {
                logger.info("Get the questions " + id1 + "  for subject " + id);
                return modelMapper.map(question, QuestionDTO.class);
            }
        }
        logger.warn("Question not found" + id);
        throw new NotFoundException("Question not found" + id);

    }

    @PostMapping(value = "/{id}/questions")
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<Question> createQuestion(@PathVariable int id,
                                         @RequestBody List<QuestionDTO> questions,
                                         @RequestHeader(name = "Authorization") String token) {
        final Subject subject = subjectService.getSubjectById(id);

        List<Question> questions1 = new ArrayList<>();
        questions.stream().forEach(x -> questions1.add(modelMapper.map(x, Question.class)));
        questions1.stream()
                .forEach(x -> x.setSubject(subject));
        subject.getQuestions().addAll(questions1);
        logger.info("Create new questions for subject " + id);
        return subjectService.updateById(subject).getQuestions();
    }

    @PutMapping(value = "/{id}/questions/{id1}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public QuestionDTO updateQuestion(@PathVariable("id") int id,
                                      @PathVariable("id1") int id1,
                                      @RequestBody QuestionDTO question,
                                      @RequestHeader(name = "Authorization") String token) {
        final Subject subject = subjectService.getSubjectById(id);
        subject.getQuestions().stream()
                .filter(x -> x.getId() == id1)
                .collect(Collectors.toList())
                .forEach(x -> {
                    List<Answer> answers = new ArrayList<>();
                    for (AnswerDTO answerDTO : question.getAnswers()){
                        answers.add(modelMapper.map(answerDTO, Answer.class));
                    }
                    x.setTimeInSeconds(question.getTimeInSeconds());
                    x.setDifficulty(question.getDifficulty());
                    x.setAnswers(answers);
                    x.setText(question.getText());
                });
        subjectService.updateById(subject);
        logger.info("Update question " + id1 + " for subject " + id);
        return question;
    }

    @DeleteMapping(value = "/{id}/questions/{id1}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") int id,
                                            @PathVariable("id1") int id1,
                                            @RequestHeader(name = "Authorization") String token) {
        Subject subject = subjectService.getSubjectById(id);
        subject.getQuestions().stream()
                .filter(x -> x.getId() == id1)
                .collect(Collectors.toList())
                .forEach(x -> subject.getQuestions().remove(x));

        subjectService.updateById(subject);
        logger.info("deleted question " + id1 + " for subject " + id);
        return null;
    }

}
