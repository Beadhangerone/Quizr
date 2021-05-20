package com.myapp1.quizr;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
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
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.myapp1.quizr.Adapters.QuestionOptionsRVAdapter;
import com.myapp1.quizr.Model.Question;
import com.myapp1.quizr.Model.QuestionOption;
import com.myapp1.quizr.Model.Quiz;
import com.myapp1.quizr.VM.QuestionVM;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionEditorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionEditorFragment extends Fragment implements QuestionOptionsRVAdapter.QuestionOptionClickListener {
    private static final String CURRENT_QUIZ = "currentQuiz";
    private static final String QUESTION_TO_EDIT = "questionToEdit";

    private Quiz quiz;
    private Question question_to_edit;
    private QuestionVM questionVM;
    private RecyclerView questionOptionsList;
    private QuestionOptionsRVAdapter questionOptionsRVAdapter;
    private Button addOptionBtn;
    private Button questionSaveBtn;

    private TextInputEditText optionTextInput;
    private TextInputEditText question_text_input;
    private MutableLiveData<List<QuestionOption>> questionOptions;


    public QuestionEditorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param quiz
     * @return A new instance of fragment newQuestionFragment.
     */
    public static QuestionEditorFragment newInstance(Quiz quiz, Question question) {
        QuestionEditorFragment fragment = new QuestionEditorFragment();
        Bundle args = new Bundle();
        args.putSerializable(CURRENT_QUIZ, quiz);
        args.putSerializable(QUESTION_TO_EDIT, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.quiz = (Quiz) getArguments().getSerializable(CURRENT_QUIZ);
            this.question_to_edit = (Question) getArguments().getSerializable(QUESTION_TO_EDIT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.question_editor_fragment, container, false);
        questionVM = new ViewModelProvider(this).get(QuestionVM.class);

        question_text_input = view.findViewById(R.id.question_text_input);
        optionTextInput = view.findViewById(R.id.optionTextInput);
        questionSaveBtn = view.findViewById(R.id.questionSaveBtn);
        addOptionBtn = view.findViewById(R.id.addOptionBtn);

        questionOptionsList = view.findViewById(R.id.questionOptionsRV);
        questionOptionsList.hasFixedSize();
        questionOptionsList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        questionOptions = new MutableLiveData<List<QuestionOption>>();
        questionOptions.setValue(new ArrayList<QuestionOption>() {});
        questionOptions.observe(getViewLifecycleOwner(), new Observer<List<QuestionOption>>() {
            @Override
            public void onChanged(List<QuestionOption> questionOptions) {
                questionOptionsRVAdapter = new QuestionOptionsRVAdapter(questionOptions, QuestionEditorFragment.this);
                questionOptionsList.setAdapter(questionOptionsRVAdapter);
            }
        });

        if(this.question_to_edit != null){
            questionOptions.setValue(questionVM.getOptionsForQuestion(question_to_edit));
            question_text_input.setText(question_to_edit.getQuestion_text());
        }else{
            this.question_to_edit = new Question();
        }

        addOptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String optionText = optionTextInput.getText().toString();

                if (!optionText.equals("")){
                    QuestionOption option = new QuestionOption();
                    option.setOption_text(optionText);
                    List<QuestionOption> options = questionOptions.getValue();
                    options.add(option);
                    questionOptions.setValue(options);
                    optionTextInput.setText("");
                }
            }
        });

        questionSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qText = question_text_input.getText().toString();
                if(qText.length() == 0){
                    Toast.makeText(v.getContext(),"Please specify the question's text",Toast.LENGTH_LONG).show();
                    return;
                }

                if(questionOptions.getValue().size() < 2){
                    Toast.makeText(v.getContext(),"Please add at least 2 options",Toast.LENGTH_LONG).show();
                    return;
                }

                question_to_edit.setQuestion_text(qText);
                question_to_edit.setQuiz_id(quiz.getId());

                questionVM.insertOptionsForQuestion(question_to_edit, questionOptions.getValue());

                NavDirections action = QuestionEditorFragmentDirections.actionNewQuestionFragmentToEditQuizFragment(quiz);
                Navigation.findNavController(view).navigate( action );

            }
        });
        return view;
    }

    @Override
    public void onMarkCorrectClick(View view, QuestionOption option) {
        List<QuestionOption> options = questionOptions.getValue();
        CheckBox checkBox = view.findViewById(R.id.isAnswerCheckBox);
        options.get(options.indexOf(option)).setIs_correct(checkBox.isChecked());
    }

    @Override
    public void onDeleteClick(View view, QuestionOption option) {
        List<QuestionOption> options = questionOptions.getValue();
        options.remove(option);
        questionOptions.setValue(options);
    }
}