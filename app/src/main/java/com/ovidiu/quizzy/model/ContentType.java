package com.ovidiu.quizzy.model;

import java.io.Serializable;

public enum ContentType implements Serializable {
    TEXT(0),
    IMAGE_WITH_TEXT(1),
    IMAGE(2);

    private int code;

    ContentType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
