package com.ovidiu.quizzy.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.ovidiu.quizzy.db.dao.QuizDao;
import com.ovidiu.quizzy.db.model.Answer;
import com.ovidiu.quizzy.db.model.Question;
import com.ovidiu.quizzy.db.model.Quiz;

@Database(entities = {Question.class, Answer.class, Quiz.class}, version = 1, exportSchema = true)
@TypeConverters({ContentConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuizDao quizDao();

}
