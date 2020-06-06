package com.finalproject.testgenerator.services;

import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.repositories.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a service for questions
 */
@Service
public class QuestionsService {

    private QuestionsRepository repository;

    @Autowired
    public QuestionsService (QuestionsRepository questionsRepository){
        this.repository = questionsRepository;
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = repository.findAll();
        if (questions.size() > 0) {
            return questions;
        } else {
            return new ArrayList<>();
        }
    }

    public Question createQuestion (Question question){
        question.setId((int) (repository.count()+1));
        question = repository.save(question);
        return question;
    }


    public void updateById(Question question) {
        repository.save(question);
    }

    public Question deleteById(int id) {
        if (repository.findById(id).isPresent()) {
            Question question = repository.findById(id).get();
            repository.deleteById(id);
            return question;
        }
        return null;
    }

    public Question getQuestionById(int id) {
        if (repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        return null;
    }

}
