package com.ovidiu.quizzy.db.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.ovidiu.quizzy.db.model.Answer;
import com.ovidiu.quizzy.db.model.Question;

import java.util.List;


public class QuestionAndAllAnswers {
    @Embedded
    private Question question;

    @Relation(parentColumn = "id", entityColumn = "question_id", entity = Answer.class)
    private List<Answer> answers;

    public QuestionAndAllAnswers() {
    }

    public QuestionAndAllAnswers(Question question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
