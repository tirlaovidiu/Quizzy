package com.ovidiu.quizzy.di;

import com.ovidiu.quizzy.view.ui.quiz.QuizViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    QuizViewModel quizViewModel();
}
