package com.myapp1.quizr.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class QuestionType {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String type;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
}
