package com.combatWombat.model;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BeerMugTest {
    private BeerMug beerMug;
    List<String> paths;

    @Before
    public void setUp() {
        beerMug = new BeerMug();
        paths = new ArrayList<>();
        paths.add("badFileText");
    }

    @Test
    public void drawBeer_shouldReturnNonNull_whenPassedCorrectScores() {
        assertNotNull(beerMug.drawBeer(50));
    }

    @Test
    public void drawBeer_shouldReturnNull_whenPassedIncorrectScores() {
        assertNull(beerMug.drawBeer(5));
    }

    @Test
    public void drawBeer_shouldReturnNotEqual_whenComparedWithDummyStringBuilder() {
        StringBuilder dumbString = new StringBuilder();
        assertNotEquals(beerMug.drawBeer(50), dumbString);
    }

    @Test
    public void createStringBuilders_shouldReturnEmptyList_whenGivenInvalidFileName(){
        List<StringBuilder> files = beerMug.createStringBuilders(paths);
        assertEquals(files.size(), 0);
    }
}