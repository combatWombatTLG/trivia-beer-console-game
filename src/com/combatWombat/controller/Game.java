package com.combatWombat.controller;


import com.apps.util.Prompter;


import com.combatWombat.model.Category;
import com.combatWombat.model.Host;
import com.combatWombat.model.Player;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import java.util.Locale;


public class Game {
    private static final int SCORE_TO_WIN = 60;
    private static final int SCORE_TO_LOSE = 0;
    private static final int STARTING_SCORE = 30;
    private static Prompter prompter;
    private List<String> beerMugs;
    private String banner;
    private Player player;
    private Host host;

    public Game() {

    }

    public Game(Prompter prompter) {
        this.prompter = prompter;
    }


    /**
     * This method will initialize the game objects and commence the game loop.
     */
    public void start() {
        banner = setBanner();
        beerMugs = setupBeerMugs();
        System.out.println(banner);
        player = getPlayer();
        host = getHost();
        while (player.getScore() < SCORE_TO_WIN && player.getScore() > SCORE_TO_LOSE) {
            try {
                clearScreen();
            } catch (IOException ignored) {

            }
            System.out.println(beerMugs.get(player.getScore() / 10));
            host.askQuestion();
            host.judgeAnswer(player.answerQuestion(prompter), player);
            askForNewGame(player, host);
        }
    }



    //ACCESSORS
    public List<String> setupBeerMugs() {
        beerMugs = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            try {
                beerMugs.add(Files.readString(Path.of("data/beer" + i * 10 + ".txt")));
            } catch (IOException e) {

            }
        }
        return beerMugs;
    }

    public String setBanner() {
        try {
            banner = Files.readString(Path.of("data/banner.txt"));
        } catch (IOException e) {

        }
        return banner;
    }

    public Host getHost() {
        String stringCategory = prompter.prompt(
                "Please choose a category : Sports, Entertainment or Science \n",
                "Sports|Entertainment|Science|sports|entertainment|science",
                "Sports, Entertainment or Science ONLY"
        );
        Category questionCategory = Category.valueOf(stringCategory.toUpperCase(Locale.ROOT));
        Host host = new Host(questionCategory);
        return host;
    }

    public Player getPlayer() {
        String userName = prompter.prompt("Please enter your name below \n");
        Player player = new Player(userName, STARTING_SCORE);
        return player;
    }

    //PRIVATE HELPER
    private void askForNewGame(Player player, Host host) {
        if (player.getScore() == 60 || player.getScore() == 0) {
            host.giveGameResult(player);
            System.out.println(beerMugs.get(beerMugs.size() -1));
            if (host.newGame(prompter)) {

                player.setScore(STARTING_SCORE);
            }
        }
    }
    private void clearScreen() throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder process = (os.contains("windows")) ?
                new ProcessBuilder("cmd", "/c", "cls") :
                new ProcessBuilder("clear");
        try {
            process.inheritIO().start().waitFor();
        } catch (InterruptedException ignored) {
        }
    }
}
