package com.ovidiu.quizzy.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.Nullable;

import com.ovidiu.quizzy.db.ContentConverter;
import com.ovidiu.quizzy.model.ContentType;


@Entity(foreignKeys = @ForeignKey(entity = Question.class, parentColumns = "id", childColumns = "question_id"))
public class Answer {
    @PrimaryKey
    private Integer id;
    @ColumnInfo(name = "type")
    @TypeConverters(ContentConverter.class)
    private ContentType type;
    @ColumnInfo(name = "title")
    private String answerTitle;
    @Nullable
    @ColumnInfo(name = "image_url")
    private String imageUrl;
    @ColumnInfo(name = "points")
    private Integer points;
    @ColumnInfo(name = "question_id")
    private Integer questionId;

    public Answer(ContentType type, String answerTitle, @Nullable String imageUrl, Integer points, Integer questionId) {
        this.type = type;
        this.answerTitle = answerTitle;
        this.imageUrl = imageUrl;
        this.points = points;
        this.questionId = questionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }

    public String getAnswerTitle() {
        return answerTitle;
    }

    public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@Nullable String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
}
