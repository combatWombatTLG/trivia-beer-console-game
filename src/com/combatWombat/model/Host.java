package com.combatWombat.model;

import com.apps.util.Prompter;

import java.io.IOException;
import java.util.*;

public class Host {
    private static final int SCORE_CHANGE_VALUE = 10;
    private static final int RANDOM_INTEGER_BOUND = 10;
    List<Question> questions;//probably try catch in the constructor
    List<Question> filteredQuestions;
    List<Integer> usedQuestions;

    public Host(Category category) {
        try {
            this.questions = new QuestionLoader("data/question-data.csv").load();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

        this.filteredQuestions = getQuestionsByCategory(category);
        this.usedQuestions = new ArrayList<>();
    }

    /**
     * This method should ask the user a question randomly from the list of questions.
     * It should also not ask the same question twice in one game.
     */
    public void askQuestion() {

        Integer questionIndex = checkIfQuestionUsed();

        //make own private helper method^^^^^

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

    public Integer checkIfQuestionUsed() {
        Random rand = new Random();
        Integer questionIndex = Integer.valueOf(rand.nextInt(RANDOM_INTEGER_BOUND));
        if (usedQuestions.contains(questionIndex)) {
            questionIndex = rand.nextInt(RANDOM_INTEGER_BOUND);
            usedQuestions.add(Integer.valueOf(questionIndex));
        } else {
            usedQuestions.add(Integer.valueOf(questionIndex));
        }
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
            System.out.println("You got it right!!~!~~~ 10 points, you are at " + player.getScore());
        } else {
            player.setScore(player.getScore() - SCORE_CHANGE_VALUE);
            System.out.println("You suck - 10 points, you are at " + player.getScore());
        }

    }

    /**
     * This method lets the player know if they have won or lost
     *
     * @param player
     */
    public void giveGameResult(Player player) {
        if (player.getScore() >= 60) {
            System.out.println("You win!!!");
        } else {
            System.out.println("you lose");
        }
    }

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

    /**
     * This allows the user to re pick their category, and it resets their score back to the middle.
     */
    public boolean newGame(Prompter prompter) {
        boolean result = false;



        String playerChoice = prompter.prompt(
                "Would you like to play another game? y or n \n",
                "Y|N|y|n",
                "y or n ONLY");
        if("y".equalsIgnoreCase(playerChoice)){

            result = true;
        } else {
            System.out.println("Thanks for playing! See you next time.");
        }
        return result;

    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setFilteredQuestions(List<Question> filteredQuestions) {
        this.filteredQuestions = filteredQuestions;
    }

    public void setUsedQuestions(List<Integer> usedQuestions) {
        this.usedQuestions = usedQuestions;
    }
}