package com.ovidiu.quizzy.db;

import android.arch.persistence.room.TypeConverter;

import com.ovidiu.quizzy.model.ContentType;


import static com.ovidiu.quizzy.model.ContentType.IMAGE;
import static com.ovidiu.quizzy.model.ContentType.IMAGE_WITH_TEXT;
import static com.ovidiu.quizzy.model.ContentType.TEXT;

public class ContentConverter {

    @TypeConverter
    public static ContentType toStatus(int status) {
        if (status == IMAGE.getCode()) {
            return IMAGE;
        } else if (status == IMAGE_WITH_TEXT.getCode()) {
            return IMAGE_WITH_TEXT;
        } else if (status == TEXT.getCode()) {
            return TEXT;
        } else {
            throw new IllegalArgumentException("Could not recognize status");
        }
    }

    @TypeConverter
    public static int toInteger(ContentType status) {
        return status.getCode();
    }
}
