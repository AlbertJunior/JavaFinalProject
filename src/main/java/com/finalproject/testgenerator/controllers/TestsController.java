package com.finalproject.testgenerator.controllers;


import com.finalproject.testgenerator.models.Test;
import com.finalproject.testgenerator.services.TestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tests")
public class TestsController {

    private final TestsService service;


    @Autowired
    public TestsController(TestsService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Test> getQuestions(@RequestParam int totalTime,
                                                       @RequestParam int subjectId){
        Test test = service.createTest(totalTime, subjectId);
        return new ResponseEntity<>(test, new HttpHeaders(), HttpStatus.OK);
    }


}
