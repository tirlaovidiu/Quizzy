package com.ovidiu.quizzy.view.ui.quiz;

import android.arch.lifecycle.ViewModel;
import android.util.SparseArray;

import com.ovidiu.quizzy.di.Injectable;
import com.ovidiu.quizzy.model.Quiz;
import com.ovidiu.quizzy.repository.UserRepository;

import java.util.HashMap;

import javax.inject.Inject;

public class QuizViewModel extends ViewModel implements Injectable {

    private Quiz quiz;
    private HashMap<Integer, Integer> score =  new HashMap<>();
    private SparseArray<String> response = new SparseArray<>();
    private UserRepository userRepo;

    @Inject
    public QuizViewModel(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void init(String quizId) {
        if (this.quiz != null) {
            // ViewModel is created on a per-Fragment basis, so the userId
            // doesn't change.
            return;
        }
        quiz = userRepo.getQuiz(quizId);
    }


    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public HashMap<Integer, Integer> getScore() {
        return score;
    }

    public void setScore(HashMap<Integer, Integer> score) {
        this.score = score;
    }

    public SparseArray<String> getResponse() {
        return response;
    }

    public void setResponse(SparseArray<String> response) {
        this.response = response;
    }
}
