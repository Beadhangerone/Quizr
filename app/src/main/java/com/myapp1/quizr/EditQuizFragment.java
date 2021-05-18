package com.myapp1.quizr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.myapp1.quizr.Adapters.EditQuestionRVAdapter;
import com.myapp1.quizr.Model.Question;
import com.myapp1.quizr.Model.Quiz;
import com.myapp1.quizr.VM.QuizEditorVM;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditQuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditQuizFragment extends Fragment implements EditQuestionRVAdapter.OnEditQuestionClickListener {

    // the fragment initialization parameters
    private static final String QUIZ_TO_EDIT = "quizToEdit";
    private Quiz quiz;
    public RecyclerView questionsList;
    private EditQuestionRVAdapter editQuestionRVAdapter;
    private Button addQuestionBtn;

    public EditQuizFragment() {
        // Required empty public constructor
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.edit_quiz_fragment, container, false);

        TextInputEditText title = view.findViewById(R.id.quizTitleInput);
        title.setText(quiz.getTitle());

        TextInputEditText description = view.findViewById(R.id.quizDescInput);
        description.setText(quiz.getDescription());

        addQuestionBtn = view.findViewById(R.id.newQuestionBtn);
        addQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = EditQuizFragmentDirections.NewQuestionAction(quiz);
                Navigation.findNavController(view).navigate( action );
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);

        Fragment questionsListFragment = new QuestionsListFragment(quiz);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.questions_list_container, questionsListFragment).commit();

    }

    @Override
    public void onEditClick(View view, Question question) {
        // TODO
    }
}