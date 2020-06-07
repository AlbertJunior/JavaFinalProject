package com.finalproject.testgenerator.algorithms;

import com.finalproject.testgenerator.exceptions.NotFoundException;
import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.models.Test;

import java.util.List;

/**
 * This is an interface for algorithms
 * We can easily add a new algorithm
 */
public interface Algorithm {

    void resolver(Test test, List<Question> questions) throws NotFoundException;
}
