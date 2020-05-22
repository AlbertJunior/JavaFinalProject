package com.finalproject.testgenerator.algorithms;

import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.models.Test;

import java.util.List;

public interface Algorithm {

    void resolver(Test test, List<Question> questions);
}
