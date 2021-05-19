package com.myapp1.quizr.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_options",
    indices = {@Index("question_id")},
    foreignKeys = {
            @ForeignKey(entity = Question.class,
                    parentColumns = "id",
                    childColumns = "question_id",
                    onDelete = ForeignKey.CASCADE)
    }
)
public class QuestionOption {
    @PrimaryKey(autoGenerate = true)
    private int id;

    // FK
    private int question_id;

    private String option_text;
    private boolean is_correct;

    public int getId() {
        return id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public String getOption_text() {
        return option_text;
    }

    public boolean isIs_correct() {
        return is_correct;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public void setOption_text(String option_text) {
        this.option_text = option_text;
    }

    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }
}
