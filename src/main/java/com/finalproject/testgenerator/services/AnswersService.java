package com.finalproject.testgenerator.services;

import com.finalproject.testgenerator.models.Answer;
import com.finalproject.testgenerator.repositories.AnswersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a service for answers
 */
@Service
public class AnswersService {

    private AnswersRepository repository;
    private Logger logger = LoggerFactory.getLogger(AnswersService.class);


    @Autowired
    public AnswersService(AnswersRepository answersRepository){
        this.repository = answersRepository;
    }

    public List<Answer> getAllAnswers() {
        List<Answer> answers = repository.findAll();
        if (answers.size() > 0) {
            logger.info("All answers were returned from repository");
            return answers;
        } else {
            logger.info("No answers were found in the repository");
            return new ArrayList<>();
        }
    }

    public Answer createAnswer(Answer answer) {
        answer.setId((int) (repository.count() + 1));
        answer = repository.save(answer);
        logger.info("An answer was created in the repository");
        return answer;
    }

    public void updateById(Answer answer) {
        repository.save(answer);
        logger.info("Answer " + answer.getId() + " was updated in the repository");
    }


    public Answer deleteById(int id) {
        if (repository.findById(id).isPresent()) {
            Answer answer = repository.findById(id).get();
            repository.deleteById(id);
            logger.info("The answer " + id + " was deleted from repository");
            return answer;
        }
        logger.info("The answer to delete was not found");
        return null;
    }

    public Answer getAnswerById(int id) {
        if (repository.findById(id).isPresent()){
            logger.info("The answer with the id " +  id + " was returned from repository");
            return repository.findById(id).get();
        }
        logger.info("The answer with the id " +  id + " was not found in the repository");
        return null;
    }

}