package com.ovidiu.quizzy.di;


import android.app.Application;

import com.ovidiu.quizzy.MVVMApplication;
import com.ovidiu.quizzy.view.QuizActivity;
import com.ovidiu.quizzy.view.ui.quiz.QuestionFragment;
import com.ovidiu.quizzy.view.ui.quiz.QuizResultFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        QuizActivityModule.class,
        FragmentBuildersModule.class,

})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(MVVMApplication mvvmApplication);
    void inject(QuizActivity quizActivity);
    void inject(QuestionFragment questionFragment);
    void inject(QuizResultFragment quizResultFragment);
}