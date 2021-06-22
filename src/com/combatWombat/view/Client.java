package com.combatWombat.view;

import com.combatWombat.controller.Game;

public class Client {
    public static void main(String[] args) {
        //pass a prompter to the game constructor
        Game game = new Game();
        game.start();
    }
}