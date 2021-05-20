package com.myapp1.quizr.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.myapp1.quizr.Model.Quiz;

import java.util.List;

@Dao
public interface QuizDAO {

    @Insert
    void insert(Quiz quiz);

    @Query("SELECT * FROM quizzes_in_dev;")
    LiveData<List<Quiz>> getMyQuizzesInDev();
}
