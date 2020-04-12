package com.ovidiu.quizzy.di;


import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.ovidiu.quizzy.db.AppDatabase;
import com.ovidiu.quizzy.db.dao.QuizDao;
import com.ovidiu.quizzy.utils.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {
    //    @Singleton
//    @Provides
//    GitHubService provideGithubService() {
//        return new Retrofit.Builder()
//                .baseUrl(GitHubService.HTTPS_API_GITHUB_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(GitHubService.class);
//    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder viewModelSubComponent) {
        return new ViewModelFactory(viewModelSubComponent.build());
    }

    @Singleton
    @Provides
    public AppDatabase provideMyDatabase(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class, "quizzy-db.db").allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    public QuizDao provideQuizDao(AppDatabase myDatabase) {
        return myDatabase.quizDao();
    }
}