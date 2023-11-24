package com.example.wafdogs.data;

import java.util.ArrayList;
import java.util.Random;

public class Question {

    private String image;
    private String correctAnswer;
    private ArrayList<String> badAnswer;

    private Question(String image, String correctAnswer) {
        this.image = image;
        this.correctAnswer = correctAnswer;
        this.badAnswer = this.createBadAnswerList();
    }

    private ArrayList<String> createBadAnswerList(){
        String[] dogsBreed = {
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

        ArrayList<String> selectedDogsBreed = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int randomIndex;
            do {
                randomIndex = random.nextInt(dogsBreed.length);
            } while (dogsBreed[randomIndex].equals(this.correctAnswer));

            selectedDogsBreed.add(dogsBreed[randomIndex]);
        }

        return selectedDogsBreed;
    }

    private String getImage() {
        return image;
    }

    private String getCorrectAnswer() {
        return correctAnswer;
    }

    private ArrayList<String> getBadAnswer() {
        return badAnswer;
    }
}
