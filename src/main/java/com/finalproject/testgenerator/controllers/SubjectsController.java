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
@Api(value="Employee Management System", description="Operations pertaining to employee in Employee Management Syste")
public class SubjectsController {

    @Autowired
    private SubjectsService service;


    @ApiOperation(value = "View a list of available subjects", response = List.class)
    @GetMapping
    public ResponseEntity<List<Subject>> getQuestions(){
        List<Subject> questions = service.getAllSubjects();
        return new ResponseEntity<>(questions, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a subject")
    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject){
        Subject subject1 = service.createSubject(subject);
        return new ResponseEntity<>(subject1, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSubject(@PathVariable("id") int id, @RequestParam String name) {
        Subject subject =  service.updateById(id);

        if (subject == null) {
            return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
        }
        subject.setName(name);
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