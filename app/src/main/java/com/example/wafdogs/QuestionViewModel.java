package com.example.wafdogs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wafdogs.data.Question;

public class QuestionViewModel extends ViewModel {

    MutableLiveData<Question> value = new MutableLiveData<>();

    MutableLiveData<Integer> lives = new MutableLiveData<>(3);
    private Question question;

    public QuestionViewModel() {
        question = new Question();
    }

    public MutableLiveData<Question> getValue() {
        return value;
    }
    public void startGame() {
        question.computeNewQuestion();
        value.postValue(question);
    }

    public void nextValue() {
        question.computeNewQuestion();
        value.postValue(question);
    }

    public LiveData<Integer> getLives() {
        return lives;
    }

    public void setLives(int value) {
        lives.setValue(value);
    }

    public void decrementLives() {
        if (lives.getValue() != null) {
            lives.setValue(lives.getValue() - 1);
        }
    }
}
