package com.ovidiu.quizzy.db.model;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;



public class QuizAndAllQuestions {

    @Embedded
    private Quiz quiz;
    @Relation(parentColumn = "id", entityColumn = "quizId", entity = Question.class)
    private List<QuestionAndAllAnswers> questions;

    public QuizAndAllQuestions() {
    }

    public QuizAndAllQuestions(Quiz quiz, List<QuestionAndAllAnswers> questions) {
        this.quiz = quiz;
        this.questions = questions;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<QuestionAndAllAnswers> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionAndAllAnswers> questions) {
        this.questions = questions;
    }
}
