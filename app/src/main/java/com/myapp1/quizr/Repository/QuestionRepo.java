package com.myapp1.quizr.Repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.myapp1.quizr.DAO.QuestionDAO;
import com.myapp1.quizr.DB.QuizrDB;
import com.myapp1.quizr.Model.Question;
import com.myapp1.quizr.Model.Quiz;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuestionRepo {
    private static QuestionRepo instance;
    private QuestionDAO questionDAO;
    private ExecutorService executorService;
    private Handler mainThreadHandler;

    public QuestionRepo(Application application) {
        QuizrDB database = QuizrDB.getInstance(application);
        questionDAO = database.questionDAO();

//        myQuizzesInDev = new LiveData<List<Quiz>>() {
//            @Override
//            public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super List<Quiz>> observer) {
//                quizDAO.getMyQuizzesInDev().observe(owner, observer);
//            }
//        };

        executorService = Executors.newFixedThreadPool(2);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    }

    public static synchronized QuestionRepo getInstance(Application app) {
        if (instance == null) {
            instance = new QuestionRepo(app);
        }
        return instance;
    }

    public LiveData<List<Question>> getQuestionsForQuiz(Quiz quiz) {
        return new LiveData<List<Question>>() {
            @Override
            public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super List<Question>> observer) {
                questionDAO.getQuestionsForQuiz(quiz.getId());
            }
        };
    }

    public void createQuestion(Question question) {
        executorService.execute(() -> questionDAO.insert(question));
    }
}