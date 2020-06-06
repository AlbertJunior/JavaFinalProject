package com.finalproject.testgenerator.services;

import com.finalproject.testgenerator.models.Subject;
import com.finalproject.testgenerator.repositories.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a service for subject
 */
@Service
public class SubjectsService {

    private SubjectsRepository repository;

    @Autowired
    public SubjectsService (SubjectsRepository subjectsRepository){
        this.repository = subjectsRepository;
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = repository.findAll();
        if (subjects.size() > 0) {
            return subjects;
        } else {
            return new ArrayList<>();
        }
    }

    public Subject createSubject(Subject subject) {
        subject.setId((int) (repository.count() + 1));
        subject = repository.save(subject);
        return subject;
    }

    public Subject updateById(Subject subject) {
        return repository.save(subject);
    }

    public Subject deleteById(int id) {
        if (repository.findById(id).isPresent()) {
            Subject subject = repository.findById(id).get();
            repository.deleteById(id);
            return subject;
        }
        return null;
    }

    public Subject getSubjectById(int id) {
        if (repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        return null;
    }
}
