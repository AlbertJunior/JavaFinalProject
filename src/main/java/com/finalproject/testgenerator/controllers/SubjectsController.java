package com.finalproject.testgenerator.controllers;

import com.finalproject.testgenerator.models.Subject;
import com.finalproject.testgenerator.services.SubjectsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subjects")
@Api(value="Subject Management System")
public class SubjectsController {

    private final SubjectsService service;

    @Autowired
    public SubjectsController (SubjectsService service){
        this.service = service;
    }


    @ApiOperation(value = "View a list of available subjects", response = List.class)
    @GetMapping
    public ResponseEntity<List<Subject>> getQuestions(){
        List<Subject> questions = service.getAllSubjects();
        return new ResponseEntity<>(questions, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Subject> getQuestion(@PathVariable int id){
        Subject question = service.getSubjectById(id);
        if (question == null){
            return new ResponseEntity<>(question, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(question, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a subject")
    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject){
        Subject subject1 = service.createSubject(subject);
        return new ResponseEntity<>(subject1, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSubject(@PathVariable("id") int id, @RequestBody Subject subject) {
        Subject subject1 =  service.getSubjectById(id);

        if (subject1 == null) {
            return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
        }
        if (subject.getName() != null) {
            subject1.setName(subject.getName());
        }
        service.updateById(subject1);
        return new ResponseEntity<>("Subject updated", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable int id) {
        Subject subject =  service.deleteById(id);

        if (subject == null) {
            return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Subject removed", HttpStatus.OK);
    }

}
