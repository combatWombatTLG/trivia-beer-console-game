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