package com.myapp1.quizr.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quizzes_in_dev")
public class Quiz {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;

    public Quiz(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
