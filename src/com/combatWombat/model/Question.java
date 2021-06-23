package com.combatWombat.model;

import java.util.*;

public class Question {


    private String questionText;
    private String correctAnswer;
    private Category category;
    private List<String> answerChoices;
    private Map<String,String> choiceMap;

    public Question(String questionText, String correctAnswer, Category category,List<String> answerChoices) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.answerChoices = setAnswerChoices(answerChoices);
    }


    //BUSINESS METHODS
    public boolean verifyAnswer(String answer){
        boolean result = false;
        if(correctAnswer.equals(choiceMap.get(answer.toUpperCase()))){
            result = true;
        }
        return result;
    }


    //ACCESSOR METHODS
    public String getQuestionText() {
        return questionText;
    }

    public Category getCategory() {
        return category;
    }

    public List<String> getAnswerChoices() {
        return answerChoices;
    }

    private List<String> setAnswerChoices(List<String> answerChoices) {
        List<String> shuffledList = new ArrayList<>();
        Collections.shuffle(answerChoices);
        for(String answer: answerChoices){
            shuffledList.add(answer);
        }
        choiceMap = new HashMap<>();
        choiceMap.put("A", shuffledList.get(0));
        choiceMap.put("B", shuffledList.get(1));
        choiceMap.put("C", shuffledList.get(2));
        choiceMap.put("D", shuffledList.get(3));
        return shuffledList;
    }
}