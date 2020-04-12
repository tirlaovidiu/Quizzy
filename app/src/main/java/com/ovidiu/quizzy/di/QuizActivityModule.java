package com.ovidiu.quizzy.di;

import com.ovidiu.quizzy.view.QuizActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class QuizActivityModule {
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract QuizActivity contributeQuizActivity();
}
