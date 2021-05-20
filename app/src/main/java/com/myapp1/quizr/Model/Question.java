package com.myapp1.quizr.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "questions",
        indices = {@Index("quiz_id")},
        foreignKeys = {
            @ForeignKey(entity = Quiz.class,
            parentColumns = "id",
            childColumns = "quiz_id",
            onDelete = ForeignKey.CASCADE)
        }
)

public class Question implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    // FK
    private int quiz_id;

    private String question_text;

    public void setId(int id) {
        this.id = id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public int getId() {
        return id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public String getQuestion_text() {
        return question_text;
    }
}
