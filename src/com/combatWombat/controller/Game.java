package com.combatWombat.controller;

import com.combatWombat.model.BeerMug;

import com.combatWombat.model.Category;
import com.combatWombat.model.Host;
import com.combatWombat.model.Player;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final int SCORE_TO_WIN = 60;
    private static final int SCORE_TO_LOSE = 0;
    private static final int STARTING_SCORE = 30;
    //private Prompter prompter;

    public Game() {

    }



    //change methods from static
    public  void start() {
        BeerMug beerMug = new BeerMug();
        System.out.println("Welcome to console trivia!");
        System.out.println("Please enter your name below");
        Scanner scan = new Scanner(System.in);
        String userName = scan.nextLine();

        //instantiate player object
        Player player = new Player(userName, STARTING_SCORE);


        //instantiate the host object
        System.out.println("Please choose a category : Sports or Movies");
        String stringCategory = scan.nextLine();
        Category questionCategory = Category.valueOf(stringCategory.toUpperCase(Locale.ROOT));
        Host host = new Host(questionCategory);


        //GAME LOGIC
        //lets have the game class do the prompting then pass in that input to the methods that require it
        //right click -> refactor -> extract method
        while (player.getScore() < SCORE_TO_WIN && player.getScore() > SCORE_TO_LOSE) {
            clearScreen();

            beerMug.drawBeer(player.getScore());
            host.askQuestion();
            host.judgeAnswer(player.answerQuestion(), player);

            if (player.getScore() == 60 || player.getScore() == 0) {
                host.giveGameResult(player);
                if (host.newGame()) {
                    player.setScore(STARTING_SCORE);
                }
            }
        }
    }

    private  void clearScreen() {
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

}