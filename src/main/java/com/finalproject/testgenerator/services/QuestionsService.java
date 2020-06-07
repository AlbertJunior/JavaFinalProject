package com.finalproject.testgenerator.services;

import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.repositories.QuestionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(QuestionsService.class);

    @Autowired
    public QuestionsService (QuestionsRepository questionsRepository){
        this.repository = questionsRepository;
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = repository.findAll();
        if (questions.size() > 0) {
            logger.info("All questions were returned from repository");
            return questions;
        } else {
            logger.info("No questions were found in the repository");
            return new ArrayList<>();
        }
    }

    public Question createQuestion (Question question){
        question.setId((int) (repository.count()+1));
        question = repository.save(question);
        logger.info("A question was created in the repository");
        return question;
    }


    public void updateById(Question question) {
        repository.save(question);
        logger.info("Question " + question.getId() + " was updated in the repository");
    }

    public Question deleteById(int id) {
        if (repository.findById(id).isPresent()) {
            Question question = repository.findById(id).get();
            repository.deleteById(id);
            logger.info("Question " + id + " was deleted from repository");
            return question;
        }
        logger.info("The question to delete was not found");
        return null;
    }

    public Question getQuestionById(int id) {
        if (repository.findById(id).isPresent()){
            logger.info("The question with the id " +  id + " was returned from repository");
            return repository.findById(id).get();
        }
        logger.info("The answer with the id " +  id + " was not found in the repository");
        return null;
    }

}
