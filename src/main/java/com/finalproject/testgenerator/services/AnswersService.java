package com.finalproject.testgenerator.services;

import com.finalproject.testgenerator.models.Answer;
import com.finalproject.testgenerator.repositories.AnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswersService {

    @Autowired
    private AnswersRepository repository;

    public List<Answer> getAllAnswers() {
        List<Answer> answers = repository.findAll();
        if (answers.size() > 0) {
            return answers;
        } else {
            return new ArrayList<>();
        }
    }

    public Answer createAnswer(Answer answer) {
        answer.setId(repository.count() + 1);
        answer = repository.save(answer);
        return answer;
    }

    public Answer updateById(int id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }
        return null;
    }

    public Answer deleteById(int id) {
        if (repository.findById(id).isPresent()) {
            Answer answer = repository.findById(id).get();
            repository.deleteById(id);
            return answer;
        }
        return null;
    }

    public Answer getAnswerById(int id) {
        if (repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        return null;
    }
}