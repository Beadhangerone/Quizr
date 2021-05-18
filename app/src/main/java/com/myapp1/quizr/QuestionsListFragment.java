package com.myapp1.quizr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp1.quizr.Adapters.EditQuestionRVAdapter;
import com.myapp1.quizr.Model.Question;
import com.myapp1.quizr.Model.Quiz;
import com.myapp1.quizr.VM.QuizEditorVM;

import java.util.List;

public class QuestionsListFragment extends Fragment implements EditQuestionRVAdapter.OnEditQuestionClickListener  {
    private QuizEditorVM quizEditorVM;
    private RecyclerView questionsList;
    private EditQuestionRVAdapter questionRVAdapter;
    private TextView questions_count;
    private Quiz quiz;


    public QuestionsListFragment(Quiz quiz) {
        this.quiz = quiz;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.questions_list_fragment, container, false);

        questions_count = view.findViewById(R.id.questions_count);

        quizEditorVM = new ViewModelProvider(this).get(QuizEditorVM.class);
        questionsList = view.findViewById(R.id.questions_RV);
        questionsList.hasFixedSize();
        questionsList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        quizEditorVM.getQuestionsForQuiz(quiz).observe(getViewLifecycleOwner(), new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                questionRVAdapter = new EditQuestionRVAdapter(questions, QuestionsListFragment.this);
                questionsList.setAdapter(questionRVAdapter);

                String count = questions.size()+"";
                questions_count.setText(count);
            }
        });
        return view;
    }

    @Override
    public void onEditClick(View view, Question question) {

    }
}
