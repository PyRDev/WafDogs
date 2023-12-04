package com.example.wafdogs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
        viewModel.setLives(3);
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

        viewModel.getLives().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer lives) {
                if (lives <= 0) {
                    // Rediriger vers WelcomeFragment ou effectuer d'autres actions
                    navigateToWelcomeFragment();
                }
            }
        });

        viewModel.getScore().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer score) {
                updateScore(score);
            }
        });

        binding.proposition1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(binding.proposition1.getText().toString());
            }
        });

        binding.proposition2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(binding.proposition2.getText().toString());
            }
        });

        binding.proposition3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(binding.proposition3.getText().toString());
            }
        });

        binding.proposition4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(binding.proposition4.getText().toString());
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

        String breed = question.getCorrectAnswer();
        String formattedBreed = breed.replace(" ", "_").toLowerCase(); // Remplace les espaces par des underscores et convertit en minuscules
        int imageResource = getResources().getIdentifier(formattedBreed, "drawable", requireActivity().getPackageName());
        if (imageResource == 0) {
            imageResource = getResources().getIdentifier("labrador", "drawable", requireActivity().getPackageName());
        }
        binding.breedImage.setImageResource(imageResource);
        binding.livesText.setText("Lives: "+ viewModel.getLives().getValue());
    }

    private void updateScore(int score) {
        binding.scoreText.setText("Score: " + score);
    }

    private void checkAnswer(String selectedAnswer) {
        String correctAnswer = viewModel.getValue().getValue().getCorrectAnswer();

        if (selectedAnswer.equals(correctAnswer)) {
            viewModel.incrementScore(100);
            Log.d("QuizFragment", "Réponse correcte!");
        } else {
            viewModel.decrementLives();
            Log.d("QuizFragment", "Réponse incorrecte!");
        }

        viewModel.nextValue();
    }

    private void navigateToWelcomeFragment(){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WelcomeFragment welcomeFragment = WelcomeFragment.newInstance();
        fragmentTransaction.replace(R.id.fragment_container_view, welcomeFragment);
        fragmentTransaction.commit();
    }
}