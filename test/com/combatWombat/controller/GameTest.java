package com.combatWombat.controller;

import com.apps.util.Prompter;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testSetUpMugsLength() {
        Game game = new Game();
        assertEquals(7, game.setupBeerMugs().size());
    }

    @Test
    public void testSetUpMugsNotNull() {
        Game game = new Game();
        assertNotEquals(null, game.setupBeerMugs().size());
    }
}