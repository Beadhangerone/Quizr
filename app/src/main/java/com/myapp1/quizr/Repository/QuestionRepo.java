package com.myapp1.quizr.Repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.myapp1.quizr.DAO.QuestionDAO;
import com.myapp1.quizr.DAO.QuestionOptionDAO;
import com.myapp1.quizr.DB.QuizrDB;
import com.myapp1.quizr.Model.Question;
import com.myapp1.quizr.Model.QuestionOption;
import com.myapp1.quizr.Model.Quiz;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuestionRepo {
    private static QuestionRepo instance;
    private QuestionDAO questionDAO;
    private QuestionOptionDAO optionDAO;
    private ExecutorService executorService;
    private Handler mainThreadHandler;

    public QuestionRepo(Application application) {
        QuizrDB database = QuizrDB.getInstance(application);
        questionDAO = database.questionDAO();
        optionDAO = database.optionDAO();

        executorService = Executors.newFixedThreadPool(2);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    }

    public static synchronized QuestionRepo getInstance(Application app) {
        if (instance == null) {
            instance = new QuestionRepo(app);
        }
        return instance;
    }

    public List<Question> getQuestionsForQuiz(Quiz quiz) {
        return questionDAO.getQuestionsForQuiz(quiz.getId());
    }

    public List<QuestionOption> getOptionsForQuestion(Question question) {
        return questionDAO.getOptionsForQuestion(question.getId());
    }

    public int insertQuestion(Question question) {
        return Long.valueOf(questionDAO.insert(question)).intValue();
    }

    public void insertOptions(List<QuestionOption> options) {
        optionDAO.insertAllOptions(options);
    }

    public void removeQuestion(Question question) {
        questionDAO.delete(question);
    }
}