package com.combatWombat.model;

import com.apps.util.Prompter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Host {
    private static final int SCORE_CHANGE_VALUE = 10;
    private static final int RANDOM_INTEGER_BOUND = 20;
    private List<Question> questions;//probably try catch in the constructor
    private List<Question> filteredQuestions;
    private List<Integer> usedQuestions;
    private Category category;

    public Host(Category category) {
        try {
            this.questions = new QuestionLoader("data/Question-Data.csv").load();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        this.usedQuestions = new ArrayList<>();
        this.filteredQuestions = getQuestionsByCategory(category);
        this.category = category;
    }

    /**
     * This method should ask the user a question randomly from the list of questions.
     * It should also not ask the same question twice in one game.
     */
    public void askQuestion() {
        Integer questionIndex = getQuestionIndex();
        formatQuestionText(questionIndex);
    }


    /**
     * This method gets an index of an unused question from the question list.
     * @return Integer the number of the index.
     */
    public Integer getQuestionIndex() {
        Random rand = new Random();
        Integer questionIndex = Integer.valueOf(rand.nextInt(RANDOM_INTEGER_BOUND));
        while (usedQuestions.contains(questionIndex)) {
            questionIndex = rand.nextInt(RANDOM_INTEGER_BOUND);
        }
        usedQuestions.add(Integer.valueOf(questionIndex));
        return questionIndex;
    }

    /**
     * This method will take in the players answer, and based on output from the Question class'
     * verifyAnswer() method, it will change the players current score.
     *
     * @param playerAnswer The players answer to the multiple choice question.
     * @param player       The player that is answering.
     */
    public void judgeAnswer(String playerAnswer, Player player) {
        //This is trying to use the last index of the usedQuestions
        if (filteredQuestions.get(usedQuestions.get(usedQuestions.size() - 1)).
                verifyAnswer(playerAnswer)) {
            player.setScore(player.getScore() + SCORE_CHANGE_VALUE);
              System.out.println("right banner goes here");
        } else {
            player.setScore(player.getScore() - SCORE_CHANGE_VALUE);
             System.out.println("wrong banner goes here");
        }

    }

    /**
     * This method lets the player know if they have won or lost
     *
     * @param player the current player of the game.
     */
    public void giveGameResult(Player player) {
        if (player.getScore() >= 60) {
            System.out.println(setUpBanner("data/winner.txt"));
        } else {
            System.out.println(setUpBanner("data/loser.txt"));
        }
    }

    /**
     * This allows the user to re pick their category, and it resets their score back to the middle.
     */
    public boolean newGame(Prompter prompter) {
        boolean result = false;

        String playerChoice = prompter.prompt(
                "Would you like to play another game? y or n \n",
                "Y|N|y|n",
                "y or n ONLY");
        if ("y".equalsIgnoreCase(playerChoice)) {
            result = true;
        } else {
            System.out.println("Thanks for playing! See you next time.");
        }
        return result;

    }

    //PRIVATE HELPERS
    private void formatQuestionText(Integer questionIndex) {
        System.out.println("Question: ");
        System.out.println(filteredQuestions.get(questionIndex).getQuestionText());
        System.out.println();
        String choiceChars = "ABCD";
        for (int i = 0; i < choiceChars.length(); i++) {
            System.out.print(choiceChars.charAt(i) +
                    ") " + filteredQuestions.get(questionIndex).getAnswerChoices().get(i));
            System.out.println();
        }
    }
    //ACCESSORS

    /**
     * This method will return the appropriate category of questions the user has requested.
     *
     * @param category the Enum category that will be the topic of questions asked
     * @return questionsByCategory a List of questions that are the selected category.
     */
    public List<Question> getQuestionsByCategory(Category category) {
        List<Question> questionsByCategory = new ArrayList<>();
        for (Question q : questions) {
            if (q.getCategory().equals(category)) {
                questionsByCategory.add(q);
            }
        }
        return questionsByCategory;
    }

    public String setUpBanner(String fileName) {
        String reader = "";
        try {
            reader = Files.readString(Path.of(fileName));
        } catch (IOException e) {

        }
        return reader;
    }
    public Category getCategory() {
        return category;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Integer> getUsedQuestions() {
        return usedQuestions;
    }

    public List<Question> getFilteredQuestions() {
        return filteredQuestions;
    }

    public static int getRandomIntegerBound() {
        return RANDOM_INTEGER_BOUND;
    }
}