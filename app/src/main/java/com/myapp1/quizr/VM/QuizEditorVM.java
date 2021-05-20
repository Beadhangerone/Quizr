package com.myapp1.quizr.VM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.myapp1.quizr.Model.Question;
import com.myapp1.quizr.Model.Quiz;
import com.myapp1.quizr.Repository.QuestionRepo;
import com.myapp1.quizr.Repository.QuizRepo;

import java.util.List;

public class QuizEditorVM extends AndroidViewModel {

    public QuestionRepo questionRepo;

    public QuizEditorVM(@NonNull Application application) {
        super(application);

        questionRepo = QuestionRepo.getInstance(application);

    }

    public LiveData<List<Question>> getQuestionsForQuiz(Quiz quiz){
        return new LiveData<List<Question>>() {
            @Override
            public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super List<Question>> observer) {
                questionRepo.getQuestionsForQuiz(quiz).observe(owner, observer);
            }
        };
    }

}
