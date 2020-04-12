package com.ovidiu.quizzy.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.io.Serializable;
import java.util.List;

public class Quiz extends BaseObservable implements Serializable {
    private String id;
    private String name;
    private String backgroundUrl;
    private List<Question> questions;
    private List<Result> results;

    public Quiz(String id, String name, String backgroundUrl, List<Question> questions, List<Result> results) {
        this.id = id;
        this.name = name;
        this.backgroundUrl = backgroundUrl;
        this.questions = questions;
        this.results = results;
    }

    public Quiz() {
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
