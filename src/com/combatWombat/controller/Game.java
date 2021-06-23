package com.combatWombat.controller;


import com.apps.util.Prompter;



import com.combatWombat.model.Category;
import com.combatWombat.model.Colors;
import com.combatWombat.model.Host;
import com.combatWombat.model.Player;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import java.util.Locale;
import java.util.Random;


public class Game {
    private static final int SCORE_TO_WIN = 60;
    private static final int SCORE_TO_LOSE = 0;
    private static final int STARTING_SCORE = 30;
    private static Prompter prompter;
    private List<String> beerMugs;
    private String banner;


    public Game() {

    }
    public Game(Prompter prompter) {
        this.prompter = prompter;
    }


    //change methods from static
    public  void start() {
        banner = setBanner();
        beerMugs = setupBeerMugs();

        System.out.println(banner);
        Player player = getPlayer();
        Host host = getHost();


        //GAME LOGIC
        //lets have the game class do the prompting then pass in that input to the methods that require it
        //right click -> refactor -> extract method

        while (player.getScore() < SCORE_TO_WIN && player.getScore() > SCORE_TO_LOSE) {
            try {
                clearScreen();
            } catch (IOException e) {

            }

            System.out.println(beerMugs.get(player.getScore() / 10));
            host.askQuestion();
            host.judgeAnswer(player.answerQuestion(prompter), player);

            if (player.getScore() == 60 || player.getScore() == 0) {
                host.giveGameResult(player);
                if (host.newGame(prompter)) {
                    player.setScore(STARTING_SCORE);
                }
            }
        }
    }



    public  Host getHost () {

        String stringCategory = prompter.prompt(
                Colors.BLUE +
                "Please choose a category : Sports, Entertainment or Science \n" + Colors.RESET,
                "Sports|Entertainment|Science|sports|entertainment|science"
                ,
                Colors.RED_BOLD + "Sports, Entertainment or Science ONLY" + Colors.RESET
        );
        Category questionCategory = Category.valueOf(stringCategory.toUpperCase(Locale.ROOT));
        Host host = new Host(questionCategory);
        return host;
    }

    public Player getPlayer () {
        String userName = prompter.prompt(Colors.BLUE + "Please enter your name below \n" + Colors.RESET);
        Player player = new Player(userName, STARTING_SCORE);
        return player;
    }


    public List<String> setupBeerMugs () {
        beerMugs = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            try {
                beerMugs.add(Colors.YELLOW + Files.readString(Path.of("data/beer" + i * 10 + ".txt")) + Colors.RESET);
            } catch (IOException e) {

            }
        }
        return beerMugs;
    }
    public String setBanner() {
        try {
          banner =  Colors.YELLOW + Files.readString(Path.of("data/banner.txt")) + Colors.RESET;
        } catch (IOException e) {

        }
        return banner;
    }


    public static void clearScreen () throws IOException {
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
