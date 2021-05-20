package com.myapp1.quizr.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.myapp1.quizr.Model.QuestionOption;

import java.util.List;

@Dao
public interface QuestionOptionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllOptions(List<QuestionOption> options);
}
