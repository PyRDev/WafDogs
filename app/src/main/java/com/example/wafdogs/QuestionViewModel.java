package com.example.wafdogs;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wafdogs.data.Question;

public class QuestionViewModel extends ViewModel {

    MutableLiveData<Question> value = new MutableLiveData<>();
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
}
