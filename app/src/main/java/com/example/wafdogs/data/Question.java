package com.example.wafdogs.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Question {

    private Map<String, String> dogsBreed = new HashMap<>();
    private String correctAnswer;
    private ArrayList<String> badAnswer;

    public Question() {
        dogsBreed.put("Labrador", "labrador.jpg");
        dogsBreed.put("German Shepherd", "german_shepherd.webp");
        dogsBreed.put("French Bulldog", "french_bulldog.webp");
        dogsBreed.put("Golden retriever", "golden_retriever.jpg");
        dogsBreed.put("Poodle", "poodle.jpg");
        dogsBreed.put("Chihuahua", "chihuahua.jpg");
        dogsBreed.put("Dalmatian", "dalmatian.webp");
        dogsBreed.put("Husky", "husky.jpeg");
        dogsBreed.put("Beagle", "beagle.jpeg");
        dogsBreed.put("English bulldog", "english_bulldog.webp");
    }

    public void computeNewQuestion() {
        this.setCorrectAnswer();
        this.setBadAnswerList();
    }

    public void setCorrectAnswer() {
        Random random = new Random();
        int randomIndex = random.nextInt(dogsBreed.size());

        correctAnswer = (String) dogsBreed.keySet().toArray()[randomIndex];
    }

    public void setBadAnswerList() {
        ArrayList<String> selectedBadAnswers = new ArrayList<>();
        Set<String> dogBreedsSet = dogsBreed.keySet();

        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            String randomDogBreed;
            do {
                randomDogBreed = (String) dogBreedsSet.toArray()[random.nextInt(dogBreedsSet.size())];
            } while (randomDogBreed.equals(correctAnswer) || selectedBadAnswers.contains(randomDogBreed));

            selectedBadAnswers.add(randomDogBreed);
        }

        this.badAnswer = selectedBadAnswers;
    }

    public ArrayList<String> getShuffledPropositions() {
        ArrayList<String> allPropositions = new ArrayList<>();
        allPropositions.add(correctAnswer);
        allPropositions.addAll(badAnswer);

        // Mélanger les propositions de manière aléatoire
        Collections.shuffle(allPropositions);

        return allPropositions;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public ArrayList<String> getBadAnswer() {
        return badAnswer;
    }
}
