package com.finalproject.testgenerator.services;

import com.finalproject.testgenerator.models.Subject;
import com.finalproject.testgenerator.repositories.SubjectsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(SubjectsService.class);


    @Autowired
    public SubjectsService (SubjectsRepository subjectsRepository){
        this.repository = subjectsRepository;
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = repository.findAll();
        if (subjects.size() > 0) {
            logger.info("All subjects were returned from repository");
            return subjects;
        } else {
            logger.info("No subjects were found in the repository");
            return new ArrayList<>();
        }
    }

    public Subject createSubject(Subject subject) {
//        subject.setId((int) (repository.count() + 5));
        subject = repository.save(subject);
        logger.info("A subject was created in the repository");
        return subject;
    }

    public Subject updateById(Subject subject) {
        logger.info("Subject " + subject.getId() + " was updated in the repository");
        return repository.save(subject);
    }

    public Subject deleteById(int id) {
        if (repository.findById(id).isPresent()) {
            Subject subject = repository.findById(id).get();
            repository.deleteById(id);
            logger.info("Subject " + id + " was deleted from repository");
            return subject;
        }
        logger.info("The subject to delete was not found");
        return null;
    }

    public Subject getSubjectById(int id) {
        if (repository.findById(id).isPresent()){
            logger.info("The subject with the id " +  id + " was returned from repository");
            return repository.findById(id).get();
        }
        logger.info("The subject with the id " +  id + " was not found in the repository");
        return null;
    }
}
