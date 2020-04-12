package com.ovidiu.quizzy.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;

public class Result extends BaseObservable implements Serializable {
    @NonNull
    private String id;
    private ContentType type;
    private String resultTitle;
    private String resultDescription;
    @Nullable
    private String imageUrl;
    private int points;

    public Result(@NonNull String id, ContentType type, String resultTitle, String resultDescription, @Nullable String imageUrl, int points) {
        this.id = id;
        this.type = type;
        this.resultTitle = resultTitle;
        this.resultDescription = resultDescription;
        this.imageUrl = imageUrl;
        this.points = points;
    }

    public Result() {
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }
    @Bindable
    public String getResultTitle() {
        return resultTitle;
    }

    public void setResultTitle(String resultTitle) {
        this.resultTitle = resultTitle;
    }
    @Bindable
    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@Nullable String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
