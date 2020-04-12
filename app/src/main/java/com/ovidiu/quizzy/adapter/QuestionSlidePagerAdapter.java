package com.ovidiu.quizzy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ovidiu.quizzy.model.Question;
import com.ovidiu.quizzy.view.ui.quiz.QuestionFragment;
import com.ovidiu.quizzy.view.ui.quiz.QuizResultFragment;

import java.util.List;

public class QuestionSlidePagerAdapter extends FragmentStatePagerAdapter {

    private List<Question> questions;

    public QuestionSlidePagerAdapter(FragmentManager fm, List<Question> questions) {
        super(fm);
        this.questions = questions;
    }

    @Override
    public int getCount() {
        return questions.size() + 1;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == questions.size()) {
            return QuizResultFragment.newInstance();
        }
        return QuestionFragment.newInstance(i);
    }

    public List<Question> getAnswers() {
        return questions;
    }

    public void setAnswers(List<Question> answers) {
        this.questions = answers;
    }
}
