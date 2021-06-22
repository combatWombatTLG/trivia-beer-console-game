package com.combatWombat.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionLoader {
    private static final int MULTIPLE_CHOICE_AMOUNT = 4;
    private Path dataFilePath;

    public QuestionLoader(String dataFilePath) {
        this.dataFilePath = Path.of(dataFilePath);
    }
    public List<Question> load() throws IOException{
        List<Question> result = new ArrayList<>();
        Files.lines(dataFilePath).forEach(line -> {
            String[] tokens = line.split(",");
            List<String> choices = new ArrayList<>();

            String questionText = tokens[0];
            Category category = Category.valueOf(tokens[1]);
            String correctAnswer = tokens[2];

            for(int i = 0; i < MULTIPLE_CHOICE_AMOUNT; i++){
                choices.add(tokens[i + 2]);
            }
           result.add(new Question(questionText,correctAnswer,category,choices));
        });
        return result;
    }
}