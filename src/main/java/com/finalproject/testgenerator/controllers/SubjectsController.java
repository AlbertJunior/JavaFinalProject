package com.finalproject.testgenerator.controllers;

import com.finalproject.testgenerator.DTOs.AnswerDTO;
import com.finalproject.testgenerator.DTOs.QuestionDTO;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/subjects")
@Api(value = "Subject Management System")
public class SubjectsController {

    private final SubjectsService subjectService;
    private ModelMapper modelMapper;
    Logger logger = LoggerFactory.getLogger(SubjectsController.class);

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


    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Subject> getSubject(@PathVariable int id) {
        Subject question = subjectService.getSubjectById(id);
        if (question == null) {
            logger.warn("Subject not found");
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        logger.info("Get a subject by id");
        return new ResponseEntity<>(question, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a subject")
    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject subject1 = subjectService.createSubject(subject);
        logger.info("The subject with the id " +  subject1.getId() + " was created");
        return new ResponseEntity<>(subject1, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSubject(@PathVariable("id") int id, @RequestBody Subject subject) {
        Subject subject1 = subjectService.getSubjectById(id);

        if (subject1 == null) {
            logger.warn("Subject not found");
            return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
        }
        if (subject.getName() != null) {
            subject1.setName(subject.getName());
        }
        logger.info("The subject with the id " +  subject1.getId() + " was updated");
        subjectService.updateById(subject1);
        return new ResponseEntity<>("Subject updated", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteSubject(@PathVariable int id) {
        Subject subject = subjectService.deleteById(id);

        if (subject == null) {
            logger.warn("Subject not found");
            return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
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
            questionDTOS.add(this.convertToDto(question));
        }
        return questionDTOS;
    }

    @GetMapping(value = "/{id}/questions/{id1}")
    @ResponseStatus(value = HttpStatus.OK)
    public QuestionDTO getQuestion(@PathVariable int id, @PathVariable int id1) {
        Subject subject = subjectService.getSubjectById(id);
        for (Question question : subject.getQuestions()){
            if (question.getId() == id1) {
                logger.info("Get all the questions for subject " + id);
                return this.convertToDto(question);
            }
        }
        logger.warn("Question not found" + id);
        return null;
    }

    @PostMapping(value = "/{id}/questions")
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<Question> createQuestion(@PathVariable int id, @RequestBody List<QuestionDTO> questions) {
        final Subject subject = subjectService.getSubjectById(id);

        List<Question> questions1 = new ArrayList<>();
        questions.stream().forEach(x -> questions1.add(this.convertToEntity(x)));
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
                                                 @RequestBody QuestionDTO question) {
        final Subject subject = subjectService.getSubjectById(id);
        subject.getQuestions().stream()
                .filter(x -> x.getId() == id1)
                .collect(Collectors.toList())
                .forEach(x -> {
                    x.setTimeInSeconds(question.getTimeInSeconds());
                    x.setDifficulty(question.getDifficulty());
                    x.setAnswers(question.getAnswers().stream().map(this::convertToEntity).collect(Collectors.toList()));
                    x.setText(question.getText());
                });
        subjectService.updateById(subject);
        logger.info("Update question " + id1 + " for subject " + id);
        return question;
    }

    @DeleteMapping(value = "/{id}/questions/{id1}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Question deleteQuestion(@PathVariable("id") int id,
                                   @PathVariable("id1") int id1) {
        Subject subject = subjectService.getSubjectById(id);
        subject.getQuestions().stream()
                .filter(x -> x.getId() == id1)
                .collect(Collectors.toList())
                .forEach(x -> subject.getQuestions().remove(x));

        subjectService.updateById(subject);
        logger.info("deleted question " + id1 + " for subject " + id);
        return null;
    }


//    --------------------------------------DTO CONVERSIONS---------------------------------------------------------

    private QuestionDTO convertToDto(Question question) {
        List<Answer> answers = question.getAnswers();
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for (Answer answer : answers){
            answerDTOS.add(this.convertToDto(answer));
            logger.warn(answerDTOS.get(0).toString());
        }

        QuestionDTO postDto = modelMapper.map(question, QuestionDTO.class);
        postDto.getAnswers().addAll(answerDTOS);
        return postDto;
    }

    private Question convertToEntity(QuestionDTO postDto) {
        Question question = modelMapper.map(postDto, Question.class);
        return question;
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
