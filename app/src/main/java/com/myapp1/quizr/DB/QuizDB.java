package com.myapp1.quizr.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.myapp1.quizr.DAO.QuizDAO;
import com.myapp1.quizr.Model.Quiz;

@Database(entities = {Quiz.class}, version = 1)
public abstract class QuizDB extends RoomDatabase {
    private static QuizDB instance;

    public abstract QuizDAO quizDAO();

    public static synchronized QuizDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    QuizDB.class, "quizr_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
