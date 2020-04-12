package com.ovidiu.quizzy.model;

import java.util.List;

public class Question {
    private String questionTitle;
    private List<Answer> answers;

    public Question(String questionTitle, List<Answer> answers) {
        this.questionTitle = questionTitle;
        this.answers = answers;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
