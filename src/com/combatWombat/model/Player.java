package com.combatWombat.model;

import java.util.Locale;
import java.util.Scanner;

public class Player {
    private String name;
    private int score;

    public Player(String name, int score){
        this.name = name;
        this.score = score;
    }

    /**
     * TODO: non "ABCD" input needs to be handled
     * assuuming Prompter prompter
     * String userInput = prompter.prompt("Please enter your answer: A, B ,C , D", "[A-D][a-d]", "Please enter only A-D")
     * regex argument
     * overloadable prompter methods for non data validated and data validated
     * only add zipped jar to project and fix the dependencies to use it
     */
    public String answerQuestion(){
        String answer = "";
        Scanner scan = new Scanner(System.in);
        answer = scan.nextLine().toUpperCase(Locale.ROOT);
        return answer;
    }

    //ACCESSOR METHODS
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}