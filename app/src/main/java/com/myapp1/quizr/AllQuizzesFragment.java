package com.myapp1.quizr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.myapp1.quizr.VM.QuizVM;

public class AllQuizzesFragment extends Fragment {

    private QuizVM quizzesVM;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.all_quizzes_fragment, container, false);
    }
}
