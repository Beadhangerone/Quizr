package com.myapp1.quizr.VM;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.myapp1.quizr.Model.Quiz;
import com.myapp1.quizr.Repository.QuizRepo;

import java.util.ArrayList;
import java.util.List;

public class QuizVM extends AndroidViewModel {

    public QuizRepo quizRepo;
    private LiveData<List<Quiz>> quizzesInDev;

    public QuizVM(@NonNull Application application) {
        super(application);
        quizRepo = QuizRepo.getInstance(application);

        quizzesInDev = new LiveData<List<Quiz>>() {
            @Override
            public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super List<Quiz>> observer) {
                quizRepo.getMyQuizzesInDev().observe(owner, observer);

            }
        };
    }

    public void createQuiz(final Quiz quiz) {
        quizRepo.createQuiz(quiz);
    }

    public LiveData<List<Quiz>> getMyQuizzesInDev(){
        return quizzesInDev;
    }
}
