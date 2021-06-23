package com.combatWombat.model;

import com.apps.util.Prompter;
import com.combatWombat.controller.Game;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class HostTest {
    private Host host;

    @Before
    public void setUp() throws Exception {
        host = new Host(Category.SPORTS);
    }

    @Test
    public void host_shouldReturnNonNullQuestionsList_whenInstantiated() {
        assertNotNull(host.getQuestions());
    }

    @Test
    public void host_shouldHaveANonNullCategory_whenInstantiated() {
        assertNotNull(host.getCategory());
    }

    @Test
    public void getQuestionIndex_shouldReturnUniqueRandomInteger_whenCalled() {
        List<Integer> randomUniqueList = new ArrayList<>();
        for(int i = 0; i < host.getRandomIntegerBound(); i++){
            Integer newAddition = host.getQuestionIndex();
            randomUniqueList.add(newAddition);
        }
        List<Integer> newList = randomUniqueList.stream().distinct().collect(Collectors.toList());
        assertTrue(newList.size() == randomUniqueList.size());
    }

    @Test
    public void getQuestionsByCategory_shouldReturnQuestionsWithCategory_whenGivenValidCategory() {
        List<Question> actual = host.getFilteredQuestions();
        assertEquals(actual,host.getQuestionsByCategory(Category.SPORTS));
    }
}