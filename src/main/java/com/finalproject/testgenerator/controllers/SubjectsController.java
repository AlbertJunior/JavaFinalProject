package com.finalproject.testgenerator.controllers;

import com.finalproject.testgenerator.DTOs.QuestionDTO;
import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.models.Subject;
import com.finalproject.testgenerator.services.SubjectsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
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
@Api(value="Subject Management System")
public class SubjectsController {

    private final SubjectsService subjectService;
    private ModelMapper modelMapper;

    @Autowired
    public SubjectsController (SubjectsService subjectService, ModelMapper modelMapper){
        this.subjectService = subjectService;
        this.modelMapper = modelMapper;
    }


    @ApiOperation(value = "View a list of available subjects", response = List.class)
    @GetMapping
    public ResponseEntity<List<Subject>> getSubjects(){
        List<Subject> questions = subjectService.getAllSubjects();
        return new ResponseEntity<>(questions, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable int id){
        Subject question = subjectService.getSubjectById(id);
        if (question == null){
            return new ResponseEntity<>(question, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(question, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a subject")
    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject){
        Subject subject1 = subjectService.createSubject(subject);
        return new ResponseEntity<>(subject1, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSubject(@PathVariable("id") int id, @RequestBody Subject subject) {
        Subject subject1 =  subjectService.getSubjectById(id);

        if (subject1 == null) {
            return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
        }
        if (subject.getName() != null) {
            subject1.setName(subject.getName());
        }
        subjectService.updateById(subject1);
        return new ResponseEntity<>("Subject updated", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable int id) {
        Subject subject =  subjectService.deleteById(id);

        if (subject == null) {
            return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Subject removed", HttpStatus.OK);
    }

    @GetMapping (value = "/{id}/questions")
    public List<QuestionDTO> getQuestions(@PathVariable int id){
        Subject subject = subjectService.getSubjectById(id);
        return subject.getQuestions().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping (value = "/{id}/questions")
    public List<Question> createQuestion(@PathVariable int id, @RequestBody List<QuestionDTO> questions){
        final Subject subject = subjectService.getSubjectById(id);

        List<Question>  questions1 = new ArrayList<>();
        questions.stream().forEach(x -> questions1.add(this.convertToEntity(x)));
        questions1.stream()
                 .forEach(x -> x.setSubject(subject));
        subject.getQuestions().addAll(questions1);
        return subjectService.updateById(subject).getQuestions();
    }

    @PutMapping (value = "/{id}/questions/{id1}")
    public ResponseEntity<String> updateQuestion(@PathVariable("id") int id,
                                                 @PathVariable("id1") int id1,
                                                 @RequestBody Question question) {
        final Subject subject = subjectService.getSubjectById(id);
        subject.getQuestions().stream()
                              .filter(x -> x.getId() == id1)
                              .collect(Collectors.toList())
                              .forEach(x -> {
                                  x.setTimeInSeconds(question.getTimeInSeconds());
                                  x.setDifficulty(question.getDifficulty());
                                  x.setAnswers(question.getAnswers());
                                  x.setText(question.getText());
                              } );
        subjectService.updateById(subject);
        return new ResponseEntity<>("Question updated", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping (value = "/{id}/questions/{id1}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") int id,
                                                 @PathVariable("id1") int id1) {
        Subject subject =  subjectService.getSubjectById(id);
        subject.getQuestions().stream()
                .filter(x -> x.getId() == id1)
                .collect(Collectors.toList())
                .forEach(x ->  subject.getQuestions().remove(x));

        subjectService.updateById(subject);
        return new ResponseEntity<>("Subject removed", HttpStatus.OK);
    }

    private QuestionDTO convertToDto(Question question) {
        QuestionDTO postDto = modelMapper.map(question, QuestionDTO.class);
        return postDto;
    }

    private Question convertToEntity(QuestionDTO postDto) {
        Question question = modelMapper.map(postDto, Question.class);
        return question;
    }

}
