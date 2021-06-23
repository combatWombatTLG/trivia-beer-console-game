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
                "Answer with A,B,C, or D \n",
                "[A-D]|[a-d]",
                "A,B,C or D ONLY");

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