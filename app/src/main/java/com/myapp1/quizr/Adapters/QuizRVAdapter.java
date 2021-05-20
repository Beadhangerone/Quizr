package com.myapp1.quizr.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp1.quizr.Model.Quiz;
import com.myapp1.quizr.R;

import java.util.List;

public class QuizRVAdapter extends RecyclerView.Adapter<QuizRVAdapter.QuizViewHolder>{
    private List<Quiz> quizzes;
    final private OnEditQuizClickListener onEditQuizClickListener;

    public QuizRVAdapter(List<Quiz> quizzes, OnEditQuizClickListener onEditQuizClickListener) {
        this.quizzes = quizzes;
        this.onEditQuizClickListener = onEditQuizClickListener;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.quiz_in_dev_fragment, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        if(quizzes != null){
            Quiz quiz = quizzes.get(position);
            if(quiz != null){
                holder.title.setText(quiz.getTitle());
                holder.description.setText(quiz.getDescription());
                holder.currentQuiz = quiz;
            }
        }
    }

    @Override
    public int getItemCount() {
        if(quizzes != null){
            return quizzes.size();
        }
        return 0;
    }

    public interface OnEditQuizClickListener {
        void onEditQuizClick(View view, Quiz quiz);
    }

    class QuizViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        Quiz currentQuiz;
        Button editBtn;
        Button deleteBtn;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.quizTitle);
            description = itemView.findViewById(R.id.quizDescription);
            editBtn = itemView.findViewById(R.id.edit_quiz_button);
            deleteBtn = itemView.findViewById(R.id.delete_quiz_button);

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEditQuizClickListener.onEditQuizClick(v, currentQuiz);
                }
            });
        }
    }
}