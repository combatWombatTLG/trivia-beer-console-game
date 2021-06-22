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

    /**
     * This method will print out to the user a text file representation of a beer mug
     * at different fill levels.
     * @param score The score is the integer that regulates what text file representation will be selected.
     */
    public StringBuilder drawBeer(int score) {

        StringBuilder testSB = null;
        switch (score) {
            case 0:
                System.out.println(beerAmounts.get(0));
                testSB = beerAmounts.get(0);
                break;
            case 10:
                System.out.println(beerAmounts.get(1));
                testSB = beerAmounts.get(1);
                break;
            case 20:
                System.out.println(beerAmounts.get(2));
                testSB = beerAmounts.get(2);
                break;
            case 30:
                System.out.println(beerAmounts.get(3));
                testSB = beerAmounts.get(3);
                break;
            case 40:
                System.out.println(beerAmounts.get(4));
                testSB = beerAmounts.get(4);
                break;
            case 50:
                System.out.println(beerAmounts.get(5));
                testSB = beerAmounts.get(5);
                break;
            case 60:
                System.out.println(beerAmounts.get(6));
                testSB = beerAmounts.get(6);
                break;
        }
        return testSB;
    }

    /**
     * This method will read in a list of file paths. It will parse the files into a StringBuilder list.
     * @param paths The list of path names for each txt file to parse
     * @return List<StringBuilder> all of the full files represented as StringBuilders
     */
    public List<StringBuilder> createStringBuilders(List<String> paths) {
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

    /**
     * This helper method will set the list of paths information.
     * @return List of Strings that contain each of the file paths.
     */
    private List<String> setPaths(){
        paths = new ArrayList<>();
        paths.add("data/beer0.txt");
        paths.add("data/beer10.txt");
        paths.add("data/beer20.txt");
        paths.add("data/beer30.txt");
        paths.add("data/beer40.txt");
        paths.add("data/beer50.txt");
        paths.add("data/beer60.txt");
        return paths;
    }

    //ACCESOR METHODS

    public List<StringBuilder> getBeerAmounts() {
        return beerAmounts;
    }

    public List<String> getPaths() {
        return paths;
    }
}
