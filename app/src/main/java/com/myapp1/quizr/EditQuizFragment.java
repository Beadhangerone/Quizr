package com.myapp1.quizr;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.myapp1.quizr.Model.Quiz;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditQuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditQuizFragment extends Fragment {

    // the fragment initialization parameters
    private static final String QUIZ_TO_EDIT = "quizToEdit";
    private Quiz quiz;

    public EditQuizFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static EditQuizFragment newInstance(Quiz quiz) {
        EditQuizFragment fragment = new EditQuizFragment();
        Bundle args = new Bundle();
        args.putSerializable(QUIZ_TO_EDIT, quiz);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            quiz = (Quiz) getArguments().getSerializable(QUIZ_TO_EDIT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.edit_quiz_fragment, container, false);

        TextInputEditText title = view.findViewById(R.id.quizTitleInput);
        title.setText(quiz.getTitle());

        TextInputEditText description = view.findViewById(R.id.quizDescInput);

        description.setText(quiz.getDescription());
        return view;
    }
}