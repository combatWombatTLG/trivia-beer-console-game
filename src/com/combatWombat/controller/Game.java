package com.combatWombat.controller;

import com.combatWombat.model.Category;
import com.combatWombat.model.Host;
import com.combatWombat.model.Player;

import java.io.IOException;
import java.util.Scanner;

public class Game {
    private static final int SCORE_TO_WIN = 60;
    private static final int SCORE_TO_LOSE = 0;
    private static final int STARTING_SCORE = 30;

    public static void main(String[] args)  {
       start();
    }

    private static void start()  {

        System.out.println("Welcome to console trivia!");
        System.out.println("Please enter your name below");
        Scanner scan = new Scanner(System.in);
        String userName = scan.nextLine();

        //instantiate player object
        Player player = new Player(userName,STARTING_SCORE);


        //instantiate the host object
        System.out.println("Please choose a category : Sports or Movies");
        String stringCategory = scan.nextLine();
        Category questionCategory = Category.valueOf(stringCategory);
        Host host = new Host(questionCategory);


        //GAME LOGIC
        while(player.getScore() < SCORE_TO_WIN && player.getScore() > SCORE_TO_LOSE){
            host.askQuestion();
            host.judgeAnswer( player.answerQuestion(), player);
        }
        host.giveGameResult(player);
        if(host.newGame()){
            scan.close();
            start();
        }

    }

}