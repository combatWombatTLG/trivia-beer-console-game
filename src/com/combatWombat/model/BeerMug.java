package com.combatWombat.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BeerMug {
    private List<StringBuilder> beerAmounts;
    private List<String> paths;

    public BeerMug() {
        beerAmounts = createStringBuilders(setPaths());
    }

    public void drawBeer(int score) {
        switch (score) {
            case 0:
                System.out.println(beerAmounts.get(0));
                break;
            case 10:
                System.out.println(beerAmounts.get(1));
                break;
            case 20:
                System.out.println(beerAmounts.get(2));
                break;
            case 30:
                System.out.println(beerAmounts.get(3));
                break;
            case 40:
                System.out.println(beerAmounts.get(4));
                break;
            case 50:
                System.out.println(beerAmounts.get(5));
                break;
            case 60:
                System.out.println(beerAmounts.get(6));
                break;
        }
    }

    private List<StringBuilder> createStringBuilders(List<String> paths) {
        List<StringBuilder> stringBuilders = new ArrayList<>();

        for (String path : paths) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                String ls = System.getProperty("line.separator");
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(ls);
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                reader.close();

                stringBuilders.add(stringBuilder);

            } catch (FileNotFoundException e) {
                e.getStackTrace();
            } catch (IOException e) {
                e.getStackTrace();
            }

        }


        return stringBuilders;
    }
    private List<String> setPaths(){
        paths = new ArrayList<>();
        paths.add("data/beer0.txt");
        paths.add("data/beer0.txt");
        paths.add("data/beer10.txt");
        paths.add("data/beer20.txt");
        paths.add("data/beer30.txt");
        paths.add("data/beer40.txt");
        paths.add("data/beer50.txt");
        return paths;
    }
}
