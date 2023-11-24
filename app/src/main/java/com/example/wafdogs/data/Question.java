package com.example.wafdogs.data;

import java.util.ArrayList;
import java.util.Random;

public class Question {

    private String image;
    private String correctAnswer;
    private ArrayList<String> badAnswer;

    private String[] dogsBreed = {
            "Labrador Retriever",
            "Berger Allemand",
            "Bulldog Français",
            "Golden Retriever",
            "Caniche",
            "Chihuahua",
            "Dalmatien",
            "Husky Sibérien",
            "Beagle",
            "Bouledogue Anglais"
    };

    public Question() {
        this.image = "image.png";
        this.correctAnswer = "";
    }

    public void computeNewQuestion() {
        this.setCorrectAnswer();
        this.setBadAnswerList();
    }

    public void setCorrectAnswer() {
        Random random = new Random();
        int randomIndex = random.nextInt(dogsBreed.length);
        this.correctAnswer = dogsBreed[randomIndex];
    }

    public void setBadAnswerList(){
        ArrayList<String> selectedDogsBreed = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int randomIndex;
            do {
                randomIndex = random.nextInt(dogsBreed.length);
            } while (dogsBreed[randomIndex].equals(this.correctAnswer));

            selectedDogsBreed.add(dogsBreed[randomIndex]);
        }

        this.badAnswer = selectedDogsBreed;
    }

    public String getImage() {
        return image;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public ArrayList<String> getBadAnswer() {
        return badAnswer;
    }
}
