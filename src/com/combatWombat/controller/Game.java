package com.combatWombat.controller;

import com.apps.util.Prompter;
import com.combatWombat.model.BeerMug;

import com.combatWombat.model.Category;
import com.combatWombat.model.Host;
import com.combatWombat.model.Player;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;

public class Game {
    private static final int SCORE_TO_WIN = 60;
    private static final int SCORE_TO_LOSE = 0;
    private static final int STARTING_SCORE = 30;
    private static Prompter prompter;


    public Game(Prompter prompter) {
        this.prompter = prompter;
    }


    public static void start() {
        BeerMug beerMug = new BeerMug();
        Player player = getPlayer();
        Host host = getHost();


        //GAME LOGIC
        while (player.getScore() < SCORE_TO_WIN && player.getScore() > SCORE_TO_LOSE) {
            clearScreen();

            beerMug.drawBeer(player.getScore());
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

    private static Host getHost() {
        String stringCategory = prompter.prompt(
                "Please choose a category : Sports, Entertainment or Science \n",
                "Sports|Entertainment|Science|sports|entertainment|science",
                "Sports, Entertainment or Science ONLY"
        );
        Category questionCategory = Category.valueOf(stringCategory.toUpperCase(Locale.ROOT));
        Host host = new Host(questionCategory);
        return host;
    }

    private static Player getPlayer() {
        String userName = prompter.prompt("Please enter your name below \n");
        Player player = new Player(userName, STARTING_SCORE);
        return player;
    }

    private static void clearScreen() {
        if (System.getProperty("os.name").contains("W")) {
            try {
                Runtime.getRuntime().exec("cls");
            } catch (IOException e) {

            }
        } else {
            try {
                Runtime.getRuntime().exec("clear");
            } catch (IOException e) {

            }
        }
    }

    public Prompter getPrompter() {
        return prompter;
    }


}