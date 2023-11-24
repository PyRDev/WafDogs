package com.example.wafdogs.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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

    public void setBadAnswerList() {
        ArrayList<String> selectedDogsBreed = new ArrayList<>();
        Set<Integer> selectedIndices = new HashSet<>();

        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int randomIndex;
            do {
                randomIndex = random.nextInt(dogsBreed.length);
            } while (selectedIndices.contains(randomIndex) || dogsBreed[randomIndex].equals(this.correctAnswer));

            selectedIndices.add(randomIndex);
            selectedDogsBreed.add(dogsBreed[randomIndex]);
        }

        this.badAnswer = selectedDogsBreed;
    }

    public ArrayList<String> getShuffledPropositions() {
        ArrayList<String> allPropositions = new ArrayList<>();
        allPropositions.add(correctAnswer);
        allPropositions.addAll(badAnswer);

        // Mélanger les propositions de manière aléatoire
        Collections.shuffle(allPropositions);

        return allPropositions;
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
