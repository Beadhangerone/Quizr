package com.myapp1.quizr.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp1.quizr.Model.Question;
import com.myapp1.quizr.R;

import java.util.List;

public class EditQuestionRVAdapter extends RecyclerView.Adapter<EditQuestionRVAdapter.EditQuestionViewHolder> {
    private List<Question> questions;
    final private OnEditQuestionClickListener onEditQuestionClickListener;

    public EditQuestionRVAdapter(List<Question> questions, OnEditQuestionClickListener onEditQuestionClickListener) {
        this.questions = questions;
        this.onEditQuestionClickListener = onEditQuestionClickListener;
    }

    @NonNull
    @Override
    public EditQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.question_fragment, parent, false);
        return new EditQuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditQuestionRVAdapter.EditQuestionViewHolder holder, int position) {
        if (questions != null){
            Question question = questions.get(position);
            if (question != null){
                holder.current_question = question;
                holder.question_text.setText(question.getQuestion_text());
            }
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public interface OnEditQuestionClickListener{
        void onEditClick(View view, Question question);
    }

    class EditQuestionViewHolder extends RecyclerView.ViewHolder{

            TextView question_text;
            Button edit_btn;
            Question current_question;

        public EditQuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            question_text = itemView.findViewById(R.id.question_text);
            edit_btn = itemView.findViewById(R.id.edit_question_btn);

            edit_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Edit Question Click");
                }
            });
        }
    }
}
