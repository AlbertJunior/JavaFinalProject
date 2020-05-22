package com.finalproject.testgenerator.services;

import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.models.Subject;
import com.finalproject.testgenerator.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestsService {

    private final SubjectsService subjectsService;
    private final QuestionsService questionsService;

    @Autowired
    public TestsService(SubjectsService subjectsService, QuestionsService questionsService) {
        this.subjectsService = subjectsService;
        this.questionsService = questionsService;
    }


    public Test createTest(int totalTime, int subjectId) {
        Subject subject = subjectsService.getSubjectById(subjectId);
        System.out.println(subject.getName());
        System.out.println("timp total " + totalTime);
        List<Question> questions = questionsService.getAllQuestions();
        Test test = new Test(totalTime, subject, questions);
        return test;
    }
}
