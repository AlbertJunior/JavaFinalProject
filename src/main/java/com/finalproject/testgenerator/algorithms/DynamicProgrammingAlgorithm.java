package com.finalproject.testgenerator.algorithms;

import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.models.Test;

import java.util.List;

import static sun.swing.MenuItemLayoutHelper.max;

public class DynamicProgrammingAlgorithm implements Algorithm{
    @Override
    public void resolver(Test test, List<Question> questions) {
        int i;
        int w;
        int[][] dp = new int[10000][10000];
        int totalTime = test.getTotalTime();

        init(dp, questions.size(), totalTime);

        for (i = 1; i <= questions.size(); i++) {
            for (w = 1; w <= totalTime; w++) {
                if (questions.get(i - 1).getTimeInSeconds() <= w) // pot sa l iau si valoarea va fi rezultatul optim al ghiozdanului
                {
                    dp[i][w] = max(dp[i - 1][w - questions.get(i - 1).getTimeInSeconds()] + questions.get(i - 1).getDifficulty(), //cu capacitate mai mica + valoarea adusa de acest obiect
                            dp[i - 1][w]); //pot sa nu l iau
                } else {
                    dp[i][w] = dp[i - 1][w]; // daca nu pot lua obiectul nici nu l iau
                }
            }
        }// pana aici am aflat castigul maxim
        constructTest(test, dp, questions); // aici aflu obiectele pe care le-am luat in solutia generata mai sus
    }
    void constructTest(Test test, int[][] dp, List<Question> questions) {

        int w = test.getTotalTime();
        int i;
        int n = questions.size();
        int value = dp[n][w];

        for (i = n; i > 0 && value > 0; i--) { // aici ma intorc ca intr-un BKT
            if (value != dp[i - 1][w]) {
                test.addQuestion(questions.get(i - 1));
                value = value - questions.get(i - 1).getDifficulty();
                w = w - questions.get(i - 1).getTimeInSeconds();
            }
        }
    }

    void init(int[][] dp, int n, int cap) { // initializarile specifice programarii dinamice
        int i;
        int w;
        for (i = 0; i < n; i++) // pentru oricate obiecte luate in calcult, daca avem capacitatea 0, castigul va fi 0
            dp[i][0] = 0;

        for (w = 0; w < cap; w++)// pentru orice capacitate a rucsacului, daca nu luam niciun obiect in calcul, castigul va fi 0
            dp[0][w] = 0;
    }
}
