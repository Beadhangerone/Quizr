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
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp1.quizr.Adapters.QuizRVAdapter;
import com.myapp1.quizr.Model.Quiz;
import com.myapp1.quizr.VM.QuizVM;

import java.util.List;

public class MyQuizzesInDevFragment extends Fragment implements QuizRVAdapter.OnEditQuizClickListener {
    private QuizVM quizVM;
    private RecyclerView myQuizzesInDevList;
    private QuizRVAdapter quizRVAdapter;
    private TextView quiz_count;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.my_quizzes_in_dev_fragment, container, false);

        quiz_count = view.findViewById(R.id.quiz_count);

        quizVM = new ViewModelProvider(this).get(QuizVM.class);

        myQuizzesInDevList = view.findViewById(R.id.rv);
        myQuizzesInDevList.hasFixedSize();
        myQuizzesInDevList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        quizVM.getMyQuizzesInDev().observe(getViewLifecycleOwner(), new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                quizRVAdapter = new QuizRVAdapter(quizzes, MyQuizzesInDevFragment.this);
                myQuizzesInDevList.setAdapter(quizRVAdapter);
                String count = quizzes.size()+"";
                quiz_count.setText(count);
            }
        });
        return view;
    }

    @Override
    public void onEditQuizClick(View view, Quiz quiz) {
        NavDirections action = MyQuizzesInDevFragmentDirections.EditQuizAction(quiz);
        Navigation.findNavController(view).navigate( action );
    }
}
