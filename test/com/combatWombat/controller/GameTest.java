package com.combatWombat.controller;

import com.apps.util.Prompter;
import com.combatWombat.model.Host;
import com.combatWombat.model.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GameTest {
    private Game gameHost;
    private Game gamePlayer;
    private Prompter prompterPlayer;
    private Prompter prompterHost;
    private Game game;
    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void setUpBeerMugs_shouldReturnSeven_whenSizeIsSeven() {
        assertEquals(7, game.setupBeerMugs().size());
    }

    @Test
    public void setUpBeerMugs_shouldReturnNotNull_WhenSizeIsSeven() {

        assertNotEquals(null, game.setupBeerMugs().size());
    }

    @Test
    public void setBanner_shouldNotBeNull_whenFileIsRead() {
        String actual = game.setBanner();
        assertNotNull(actual);
    }

    @Test
    public void getPlayer_shouldReturn30_whenPlayerMade() throws FileNotFoundException {
        prompterPlayer = new Prompter(new Scanner(new File("data/responsePlayer.txt")));
        gamePlayer = new Game(prompterPlayer);
        Player player = gamePlayer.getPlayer();
        assertEquals(30, player.getScore());
    }
    @Test
    public void getPlayer_shouldReturnName_whenGivenValidName() throws FileNotFoundException {
        prompterPlayer = new Prompter(new Scanner(new File("data/responsePlayer.txt")));
        gamePlayer = new Game(prompterPlayer);
        Player player = gamePlayer.getPlayer();
        assertEquals("nick", player.getName());
    }

    @Test
    public void getHost_shouldReturnCategory_whenGivenValidCategory() throws FileNotFoundException {
        prompterHost = new Prompter(new Scanner(new File("data/responseHost.txt")));
        gameHost = new Game(prompterHost);
        Host host = gameHost.getHost();
        assertEquals("SPORTS", host.getCategory().toString());
    }


}