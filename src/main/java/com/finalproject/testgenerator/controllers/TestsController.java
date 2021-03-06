package com.finalproject.testgenerator.controllers;


import com.finalproject.testgenerator.exceptions.NotFoundException;
import com.finalproject.testgenerator.models.Test;
import com.finalproject.testgenerator.services.TestsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Tests Management System")
@RequestMapping("api/v1/tests")
public class TestsController {

    private final TestsService service;
    private Logger logger = LoggerFactory.getLogger(TestsController.class);

    @Autowired
    public TestsController(TestsService service) {
        this.service = service;
    }


    @ApiOperation(value = "Get a optimum test for a subject", response = List.class)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Test getQuestions(@RequestParam int totalTime,
                             @RequestParam int subjectId) throws NotFoundException {
        Test test = service.createTest(totalTime, subjectId);
        logger.info("A new optimum test was return to a client");
        return test;
    }


}
