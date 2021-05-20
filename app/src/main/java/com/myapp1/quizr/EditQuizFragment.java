package com.myapp1.quizr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
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
public class EditQuizFragment extends Fragment implements EditQuestionRVAdapter.QuestionClickListener {

    // the fragment initialization parameters
    private static final String QUIZ_TO_EDIT = "quizToEdit";
    private Quiz quiz;
    private List<Question> questions;
    private QuizEditorVM quizEditorVM;
    public RecyclerView questionsList;
    private EditQuestionRVAdapter editQuestionRVAdapter;
    private TextView questionCount;
    private Button addQuestionBtn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.edit_quiz_fragment, container, false);

        questionCount = view.findViewById(R.id.questionCount);

        quizEditorVM = new ViewModelProvider(this).get(QuizEditorVM.class);

        questionsList = view.findViewById(R.id.questionListRV);
        questionsList.hasFixedSize();
        questionsList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        questions = quizEditorVM.getQuestionsForQuiz(quiz);

        editQuestionRVAdapter = new EditQuestionRVAdapter(questions, this);
        questionsList.setAdapter(editQuestionRVAdapter);
        questionCount.setText(questions.size()+"");

        TextInputEditText title = view.findViewById(R.id.quizTitleInput);
        title.setText(quiz.getTitle());

        TextInputEditText description = view.findViewById(R.id.quizDescInput);
        description.setText(quiz.getDescription());

        addQuestionBtn = view.findViewById(R.id.newQuestionBtn);
        addQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = EditQuizFragmentDirections.OpenQuestionEditor(quiz, null);
                Navigation.findNavController(view).navigate( action );
            }
        });

        return view;
    }

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

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            Fragment fragment = new LogInFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
        }

        if (getArguments() != null) {
            quiz = (Quiz) getArguments().getSerializable(QUIZ_TO_EDIT);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onEditClick(View view, Question question) {
        NavDirections action = EditQuizFragmentDirections.OpenQuestionEditor(quiz, question);
        Navigation.findNavController(view).navigate( action );
    }

    @Override
    public void onDeleteClick(View view, Question question) {
        new AlertDialog.Builder(this.getContext())
                .setTitle("Delete Question")
                .setMessage("Do you really want to delete this question?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        quizEditorVM.removeQuestion(question);
                        questions.remove(question);
                        editQuestionRVAdapter = new EditQuestionRVAdapter(questions, EditQuizFragment.this);
                        questionsList.setAdapter(editQuestionRVAdapter);
                        questionCount.setText(questions.size()+"");
                        Toast.makeText(EditQuizFragment.this.getContext(), "Question Deleted", Toast.LENGTH_SHORT).show();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }
}