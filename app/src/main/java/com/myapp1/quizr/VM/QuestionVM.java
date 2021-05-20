package com.myapp1.quizr.VM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.myapp1.quizr.Model.Question;
import com.myapp1.quizr.Model.QuestionOption;
import com.myapp1.quizr.Repository.QuestionRepo;

import java.util.List;

public class QuestionVM extends AndroidViewModel {

    private QuestionRepo questionRepo;
    private LiveData<List<QuestionOption>> options;

    public QuestionVM(@NonNull Application application) {
        super(application);

        questionRepo = QuestionRepo.getInstance(application);
    }

    public LiveData<List<QuestionOption>> getOptionsForQuestion(Question question){
        options = new LiveData<List<QuestionOption>>() {
            @Override
            public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super List<QuestionOption>> observer) {
                questionRepo.getOptionsForQuestion(question).observe(owner, observer);
            }
        };

        return options;
    }

    public void insertOptionsForQuestion(Question question, List<QuestionOption> options) {
        if(options.size() < 2){
            throw new RuntimeException("Cannot create a question with less than 2 options");
        }

        int id = questionRepo.insertQuestion(question);

        if (id <= 0){
            throw new RuntimeException("Cannot insert options for question with id <= 0");
        }

        for (QuestionOption option: options) {
            option.setQuestion_id(id);
        }
        questionRepo.insertOptions(options);
    }
}
