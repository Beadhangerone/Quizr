package com.myapp1.quizr.DAO;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.myapp1.quizr.Model.Question;
import com.myapp1.quizr.Model.QuestionOption;
import com.myapp1.quizr.Model.Quiz;

import java.util.List;

@Dao
public interface QuestionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Question question);

    @Delete
    void delete(Question question);

    @Query("SELECT * FROM questions where quiz_id = :quiz_id;")
    List<Question> getQuestionsForQuiz(int quiz_id);

    @Query("SELECT * FROM question_options where question_id = :question_id;")
    List<QuestionOption> getOptionsForQuestion(int question_id);
}