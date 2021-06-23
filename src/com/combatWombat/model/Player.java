package com.combatWombat.model;

import com.apps.util.Prompter;

import java.util.Locale;

public class Player {
    private String name;
    private int score;



    public Player(String name, int score){
        this.name = name;
        this.score = score;
    }

    public String answerQuestion(Prompter prompter){
        String answer = prompter.prompt(
                Colors.BLUE_BRIGHT + "Answer with A,B,C, or D \n" + Colors.RESET,
                "[A-D]|[a-d]",
                Colors.RED_BOLD + "A,B,C or D ONLY" + Colors.RESET);

        return answer;
    }

    //ACCESSOR METHODS

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}