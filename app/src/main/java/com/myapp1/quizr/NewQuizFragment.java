package com.myapp1.quizr;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.myapp1.quizr.Model.Quiz;
import com.myapp1.quizr.VM.QuizVM;

public class NewQuizFragment extends Fragment {

    private QuizVM quizVM;

    private EditText quizTitleInput;
    private EditText quizDescInput;
    private Button submitButton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.new_quiz_fragment, container, false);
        quizVM = new ViewModelProvider(this).get(QuizVM.class);

        quizTitleInput = view.findViewById(R.id.quizTitleInput);
        quizDescInput = view.findViewById(R.id.quizDescInput);
        submitButton = view.findViewById(R.id.submitBtn);

        // LISTENERS
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuiz(v);
            }
        });

        return view;
    }

    public void saveQuiz(View v) {
        quizVM.createQuiz(new Quiz(quizTitleInput.getText().toString(), quizDescInput.getText().toString()));

        Navigation.findNavController(v).navigate(R.id.nav_all_quizzes);
    }

}
