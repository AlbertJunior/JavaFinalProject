package com.finalproject.testgenerator.algorithms;

import com.finalproject.testgenerator.exceptions.NotFoundException;
import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.models.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static sun.swing.MenuItemLayoutHelper.max;

/**
 * This class is a functional one which creates a test from a list of question and a time limit
 */
public class DynamicProgrammingAlgorithm implements Algorithm{
    private int timeLimit = 100;
    private int maxNumberOfQuestions = 100;
    private Logger logger = LoggerFactory.getLogger(DynamicProgrammingAlgorithm.class);

    /**
     * This is the main method of this class
     * It calculates the max difficulty of a test with a given time limit and a list of questions
     * @param test
     * @param questions
     */
    @Override
    public void resolver(Test test, List<Question> questions) throws NotFoundException {
        int i;
        int w;
        int[][] dp = new int[maxNumberOfQuestions][timeLimit];
        int totalTime = test.getTotalTime();

        if (questions == null || questions.size()==0){
            logger.error("No question found");
            throw new NotFoundException("No questions was found");
        }

        init(dp, questions.size(), totalTime);

        for (i = 1; i <= questions.size(); i++) {
            for (w = 1; w <= totalTime; w++) {
                if (questions.get(i - 1).getTimeInSeconds() <= w) //I can choose this question and it's optimum
                {
                    dp[i][w] = max(dp[i - 1][w - questions.get(i - 1).getTimeInSeconds()] + questions.get(i - 1).getDifficulty(), //I can take this item
                            dp[i - 1][w]); //I can skip this item
                } else {
                    dp[i][w] = dp[i - 1][w]; // If I don't have enough time, I need to skip it too
                }
            }
        }// now I have the max difficulty in dp[questions.size()][timeLimit]
        logger.info("Max difficulty of test is " + dp[questions.size()][test.getTotalTime()]);
        constructTest(test, dp, questions);
    }

    /**
     * This method constructs an optimum test with a given max difficulty for a certain time
     * @param test
     * @param dp
     * @param questions
     */
    private void constructTest(Test test, int[][] dp, List<Question> questions) {

        int w = test.getTotalTime();
        int i;
        int n = questions.size();
        int value = dp[n][w];

        for (i = n; i > 0 && value > 0; i--) { //this is a back step (like in BKT)
            if (value != dp[i - 1][w]) {
                test.addQuestion(questions.get(i - 1));
                value = value - questions.get(i - 1).getDifficulty();
                w = w - questions.get(i - 1).getTimeInSeconds();
            }
        }
    }

    /**
     * This method initializes dp structure for dynamic programming algorithm
     * @param dp
     * @param n
     * @param cap
     */
    private void init(int[][] dp, int n, int cap) {
        int i;
        int w;
        for (i = 0; i < n; i++) // Time = 0 -> difficulty=0 (0 questions)
            dp[i][0] = 0;

        for (w = 0; w < cap; w++) // 0 questions -> difficulty=0
            dp[0][w] = 0;
    }
}
