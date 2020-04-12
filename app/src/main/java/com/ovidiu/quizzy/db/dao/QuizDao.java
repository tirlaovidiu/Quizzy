package com.ovidiu.quizzy.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.ovidiu.quizzy.db.model.Question;
import com.ovidiu.quizzy.db.model.QuestionAndAllAnswers;
import com.ovidiu.quizzy.db.model.Quiz;
import com.ovidiu.quizzy.db.model.QuizAndAllQuestions;


@Dao
public interface QuizDao {
    @Query("SELECT * FROM Quiz WHERE id = :quizId")
    QuizAndAllQuestions loadQuizAllQuestions(int quizId);

    @Insert
    void insertAll(Quiz... quizzes);

    @Insert
    void insertAll(Question... questions);

    @Query("SELECT * FROM Quiz WHERE id = :quizId")
    Quiz getQuizById(int quizId);
}
