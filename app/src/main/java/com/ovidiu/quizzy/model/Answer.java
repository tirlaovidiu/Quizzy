package com.ovidiu.quizzy.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Answer {
    @NonNull
    private String id;
    private ContentType type;
    private String answerTitle;
    @Nullable
    private String imageUrl;
    private Integer points;

    public Answer(@NonNull String id, ContentType type, String answerTitle, @Nullable String imageUrl, Integer points) {
        this.id = id;
        this.type = type;
        this.answerTitle = answerTitle;
        this.points = points;
        this.imageUrl = imageUrl;
    }

    public Answer() {
    }

    public Answer(@NonNull String id, ContentType type, String answerTitle, int points) {
        this.id = id;
        this.type = type;
        this.answerTitle = answerTitle;
        this.points = points;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
