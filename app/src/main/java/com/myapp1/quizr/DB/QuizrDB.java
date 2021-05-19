package com.myapp1.quizr.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.myapp1.quizr.DAO.QuestionDAO;
import com.myapp1.quizr.DAO.QuizDAO;
import com.myapp1.quizr.Model.Question;
import com.myapp1.quizr.Model.QuestionOption;
import com.myapp1.quizr.Model.QuestionType;
import com.myapp1.quizr.Model.Quiz;

@Database(entities = {Quiz.class, Question.class, QuestionOption.class}, version = 3)
public abstract class QuizrDB extends RoomDatabase {
    private static QuizrDB instance;

    public abstract QuizDAO quizDAO();

    public abstract QuestionDAO questionDAO();

    public static synchronized QuizrDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    QuizrDB.class, "quizr_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
