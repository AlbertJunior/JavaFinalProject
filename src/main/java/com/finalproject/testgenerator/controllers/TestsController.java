package com.finalproject.testgenerator.controllers;


import com.finalproject.testgenerator.exceptions.NotFoundException;
import com.finalproject.testgenerator.models.Test;
import com.finalproject.testgenerator.services.TestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tests")
public class TestsController {

    private final TestsService service;


    @Autowired
    public TestsController(TestsService service) {
        this.service = service;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Test getQuestions(@RequestParam int totalTime,
                             @RequestParam int subjectId) throws NotFoundException {
        Test test = service.createTest(totalTime, subjectId);
        return test;
    }


}
