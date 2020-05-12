package com.finalproject.testgenerator.services;

import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.repositories.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsService {

    @Autowired
    private QuestionsRepository repository;

    public List<Question> getAllQuestions() {
        List<Question> questions = repository.findAll();
        if (questions.size() > 0) {
            return questions;
        } else {
            return new ArrayList<>();
        }
    }

    public Question createQuestion (Question question){
        question.setId((long) 5);
        question.setText("Intrebare noua??");
        question = repository.save(question);
        return question;
    }

}
