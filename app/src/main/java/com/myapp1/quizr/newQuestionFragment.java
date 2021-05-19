package com.myapp1.quizr;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapp1.quizr.Model.Quiz;
import com.myapp1.quizr.VM.QuestionVM;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link newQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class newQuestionFragment extends Fragment {
    private static final String CURRENT_QUIZ = "currentQuiz";

    private Quiz quiz;
    private QuestionVM questionVM;
    private RecyclerView questionOptionsList;



    public newQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param quiz
     * @return A new instance of fragment newQuestionFragment.
     */
    public static newQuestionFragment newInstance(Quiz quiz) {
        newQuestionFragment fragment = new newQuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(CURRENT_QUIZ, quiz);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.quiz = (Quiz) getArguments().getSerializable(CURRENT_QUIZ);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.new_question_fragment, container, false);
        questionVM = new ViewModelProvider(this).get(QuestionVM.class);

        return view;
    }
}