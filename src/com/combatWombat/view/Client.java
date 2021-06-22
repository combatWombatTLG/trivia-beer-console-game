package com.combatWombat.view;

import com.apps.util.Prompter;
import com.combatWombat.controller.Game;

import java.util.Scanner;
public class Client {
    public static void main(String[] args) {
        Prompter prompter = new Prompter(new Scanner(System.in));
        Game game = new Game(prompter);
        game.start();
    }
}