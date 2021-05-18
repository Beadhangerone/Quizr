package com.myapp1.quizr.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.myapp1.quizr.Model.Question;
import com.myapp1.quizr.Model.Quiz;

import java.util.List;

@Dao
public interface QuestionDAO {

    @Insert
    void insert(Question question);

    @Delete
    void delete(Question question);

    @Query("SELECT * FROM questions where quiz_id = :quiz_id;")
    LiveData<List<Question>> getQuestionsForQuiz(int quiz_id);
}