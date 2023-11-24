package com.example.wafdogs;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wafdogs.data.Question;

public class QuestionViewModel extends ViewModel {

    private Question question;

    MutableLiveData<String> value = new MutableLiveData<>("");

    public QuestionViewModel() {
        question = new Question();
    }

    public void startGame() {
        question.computeNewQuestion();
        String correctAnswer = question.getCorrectAnswer();
        value.postValue(correctAnswer);
    }
}
