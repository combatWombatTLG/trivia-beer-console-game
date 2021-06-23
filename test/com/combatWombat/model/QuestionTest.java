package com.combatWombat.model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class QuestionTest {
    Question testingQuestion;
    List<String> testingAnswerChoices;
    List<String> testingAnswerChoicesOrigionalOrder;

    @Before
    public void setUp() throws Exception {
        testingAnswerChoices = Arrays.asList("good", "bad", "na", "others");
        testingAnswerChoicesOrigionalOrder = List.copyOf(testingAnswerChoices);
        testingQuestion = new Question("what is good?", "good", Category.ENTERTAINMENT, testingAnswerChoices);

    }

    @Test
    public void testVerifyAnswer_correctAnswerReturnTrue() {
        String userInputCorrectAnswer = null;
        for (Map.Entry<String, String> entry : testingQuestion.getChoiceMap().entrySet()) {
            if (entry.getValue().equals("good")) {
                userInputCorrectAnswer = entry.getKey();
            }
        }
        assertTrue(testingQuestion.verifyAnswer(userInputCorrectAnswer));
    }


    @Test
    public void testVerifyAnswer_incorrectAnswerReturnFalse() {
        String userInputWrongAnswer = null;
        for (Map.Entry<String, String> entry : testingQuestion.getChoiceMap().entrySet()) {
            if (!entry.getValue().equals("good")) {
                userInputWrongAnswer = entry.getKey();
            }
        }
        assertFalse(testingQuestion.verifyAnswer(userInputWrongAnswer));
    }

    @Test
    public void testSetAnswerChoice_shouldReturnFalse() {
        List<String> testingShuffledAnswer = testingQuestion.getAnswerChoices();
        assertFalse(testingShuffledAnswer.equals(testingAnswerChoicesOrigionalOrder));
    }

}

