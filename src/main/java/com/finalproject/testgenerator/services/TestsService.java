package com.finalproject.testgenerator.services;

import com.finalproject.testgenerator.exceptions.NotFoundException;
import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.models.Subject;
import com.finalproject.testgenerator.models.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class represents a service for generating test
 */
@Service
public class TestsService {

    private final SubjectsService subjectsService;
    private final QuestionsService questionsService;
    private Logger logger = LoggerFactory.getLogger(TestsService.class);


    @Autowired
    public TestsService(SubjectsService subjectsService, QuestionsService questionsService) {
        this.subjectsService = subjectsService;
        this.questionsService = questionsService;
    }


    /**
     * This method returns a new optimum test
     * @param totalTime
     * @param subjectId
     * @return
     * @throws NotFoundException
     */
    public Test createTest(int totalTime, int subjectId) throws NotFoundException {
        Subject subject = subjectsService.getSubjectById(subjectId);
        if (subject == null) {
            logger.error("Subject " + subjectId + " was not found");
            throw new NotFoundException("Subject " + subjectId + " was not found");
        }
        System.out.println(subject.getName());
        System.out.println("timp total " + totalTime);
        List<Question> questions = questionsService.getAllQuestions();
        Test test = new Test(totalTime, subject, questions);
        logger.info("A new optimum test for subject " + subject.getName() + "was returned");
        return test;
    }
}
