package com.combatWombat.model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionLoaderTest {

    QuestionLoader testQuestionLoader;
    List<Question> testExpectedResults;
    List<String> testingAnswerChoices;

    @Before
    public void setUp() {
        testQuestionLoader = new QuestionLoader("data/QuestionLoaderTest.csv");
        List<String> testingAnswerChoices = Arrays.asList("correctAns", "wrongAns1", "wrongAns2", "wrongAns3");
        Question testingQuestion = new Question("question", "good", Category.SPORTS, testingAnswerChoices);
        testExpectedResults = Arrays.asList(testingQuestion);
    }

    @Test
    public void testLoad_shouldReturnTrue_whenCorrectLoading() throws IOException{
        List<Question> importTestQuestionList = testQuestionLoader.load();
        Question importTestQuestion = importTestQuestionList.get(0);

        assertEquals("question", importTestQuestion.getQuestionText());
        assertEquals(Category.SPORTS, importTestQuestion.getCategory());
        assertEquals(4, importTestQuestion.getAnswerChoices().size());
        assertTrue(importTestQuestion.getAnswerChoices().containsAll(
                Arrays.asList("correctAns", "wrongAns1", "wrongAns2", "wrongAns3")));

    }
}