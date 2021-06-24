package com.combatWombat.model;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.Assert.assertNotNull;

public class PlayerTest {
    private Prompter prompter;

    @Test(expected = NoSuchElementException.class)
    public void answerQuestion_shouldExceptNoSuchElementException_whenNotGivenAppropriateResponse() throws FileNotFoundException {
        prompter = new Prompter(new Scanner(new File("data/responsePlayer.txt")));
        Player player = new Player("nick", 30);
        String answer = player.answerQuestion(prompter);
    }

    @Test
    public void answerQuestion_shouldReturnNotNull_whenGivenAppropriateResponseUpperCase() throws FileNotFoundException {
        prompter = new Prompter(new Scanner(new File("data/responsePlayerUpperCaseAnswer.txt")));
        Player player = new Player("nick", 30);
        String answer = player.answerQuestion(prompter);
        assertNotNull(answer);
    }

    @Test
    public void answerQuestion_shouldReturnNotNull_whenGivenAppropriateResponseLowerCase() throws FileNotFoundException {
        prompter = new Prompter(new Scanner(new File("data/responsePlayerLowerCaseAnswer.txt")));
        Player player = new Player("nick", 30);
        String answer = player.answerQuestion(prompter);
        assertNotNull(answer);
    }

}