package com.ovidiu.quizzy.db.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Quiz.class, parentColumns = "id", childColumns = "quizId"))
public class Question {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name = "title")
    private String questionTitle;
    @ColumnInfo(name = "quizId")
    private Integer quizId;

    public Question(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }
}
