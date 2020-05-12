package com.finalproject.testgenerator.services;

import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.models.Subject;
import com.finalproject.testgenerator.repositories.QuestionsRepository;
import com.finalproject.testgenerator.repositories.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectsService {
    @Autowired
    private SubjectsRepository repository;

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = repository.findAll();
        if (subjects.size() > 0) {
            return subjects;
        } else {
            return new ArrayList<>();
        }
    }

    public Subject createSubject(Subject subject) {
        subject.setId(repository.count() + 1);
        subject = repository.save(subject);
        return subject;
    }

    public Subject updateById(int id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }
        return null;
    }

    public Subject deleteById(int id) {
        if (repository.findById(id).isPresent()) {
            Subject subject = repository.findById(id).get();
            repository.deleteById(id);
            return subject;
        }
        return null;
    }
}
