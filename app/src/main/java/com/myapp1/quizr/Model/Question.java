package com.myapp1.quizr.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions",
        indices = {@Index("quiz_id"), @Index("question_type_id")},
        foreignKeys = {
            @ForeignKey(entity = Quiz.class,
            parentColumns = "id",
            childColumns = "quiz_id",
            onDelete = ForeignKey.CASCADE),
            @ForeignKey(entity = QuestionType.class,
            parentColumns = "id",
            childColumns = "question_type_id",
            onDelete = ForeignKey.CASCADE)
        }
)

public class Question {
    @PrimaryKey(autoGenerate = true)
    private int id;

    // FK
    private int quiz_id;
    private int question_type_id;

    private String question_text;

    public void setId(int id) {
        this.id = id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public void setQuestion_type_id(int question_type_id) {
        this.question_type_id = question_type_id;
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

    public int getQuestion_type_id() {
        return question_type_id;
    }

    public String getQuestion_text() {
        return question_text;
    }
}
