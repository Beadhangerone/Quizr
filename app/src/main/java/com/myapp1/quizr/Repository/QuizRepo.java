package com.myapp1.quizr.Repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.myapp1.quizr.DAO.QuizDAO;
import com.myapp1.quizr.DB.QuizrDB;
import com.myapp1.quizr.Model.Quiz;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuizRepo {
    private static QuizRepo instance;
    private final QuizDAO quizDAO;
    private final LiveData<List<Quiz>> myQuizzesInDev;
    private final ExecutorService executorService;
    private Handler mainThreadHandler;

    public QuizRepo(Application application) {
        QuizrDB database = QuizrDB.getInstance(application);
        quizDAO = database.quizDAO();

        myQuizzesInDev = new LiveData<List<Quiz>>() {
            @Override
            public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super List<Quiz>> observer) {
                quizDAO.getMyQuizzesInDev().observe(owner, observer);
            }
        };

        executorService = Executors.newFixedThreadPool(2);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    }

    public static synchronized QuizRepo getInstance(Application application){
        if(instance == null){
            instance = new QuizRepo(application);
        }
        return instance;
    }

    public LiveData<List<Quiz>> getMyQuizzesInDev(){
        return myQuizzesInDev;
    }

    public void createQuiz(Quiz quiz) {
        executorService.execute(() -> quizDAO.insert(quiz));
    }
}
