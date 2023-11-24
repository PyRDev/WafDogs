package com.example.wafdogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wafdogs.data.Question;
import com.example.wafdogs.databinding.FragmentQuizBinding;

import java.util.ArrayList;

public class QuizFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private QuestionViewModel viewModel;

    private FragmentQuizBinding binding;

    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment newInstance() {
        QuizFragment fragment = new QuizFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.startGame();
        viewModel.value.observe(getViewLifecycleOwner(), new Observer<Question>() {
            @Override
            public void onChanged(Question question) {
                updateValues(question);
            }
        });
    }

    private void updateValues(Question question) {
        ArrayList<String> propositions = question.getShuffledPropositions();
        if (propositions.size() >= 4) {
            binding.proposition1.setText(propositions.get(0));
            binding.proposition2.setText(propositions.get(1));
            binding.proposition3.setText(propositions.get(2));
            binding.proposition4.setText(propositions.get(3));
        }
    }
}