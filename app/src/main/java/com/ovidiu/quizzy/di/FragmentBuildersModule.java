package com.ovidiu.quizzy.di;


import com.ovidiu.quizzy.view.ui.quiz.QuestionFragment;
import com.ovidiu.quizzy.view.ui.quiz.QuizResultFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract QuestionFragment contributeQuestionFragment();

    @ContributesAndroidInjector
    abstract QuizResultFragment contributeQuizResultFragment();

}