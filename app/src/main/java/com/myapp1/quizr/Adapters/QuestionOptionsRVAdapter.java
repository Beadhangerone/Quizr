package com.myapp1.quizr.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.myapp1.quizr.Model.QuestionOption;
import com.myapp1.quizr.R;

import java.util.List;

public class QuestionOptionsRVAdapter extends RecyclerView.Adapter<QuestionOptionsRVAdapter.QuestionOptionViewHolder> {

    private List<QuestionOption> options;

    final private QuestionOptionClickListener questionOptionClickListener;

    public QuestionOptionsRVAdapter(List<QuestionOption> options, QuestionOptionClickListener questionOptionClickListener) {
        this.options = options;
        this.questionOptionClickListener = questionOptionClickListener;
    }

    @NonNull
    @Override
    public QuestionOptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.question_option_fragment, parent, false);
        return new QuestionOptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionOptionViewHolder holder, int position) {
        if (options != null){
            QuestionOption questionOption = options.get(position);

            if(questionOption != null){
                holder.currentOption = questionOption;
                holder.option_text.setText(questionOption.getOption_text());
                holder.isAnswerCheckBox.setChecked(questionOption.isIs_correct());
            }
        }
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public interface QuestionOptionClickListener {
        void onMarkCorrectClick(View view, QuestionOption option);
        void onDeleteClick(View view, QuestionOption option);
    }

    class QuestionOptionViewHolder extends RecyclerView.ViewHolder{

        QuestionOption currentOption;
        TextView option_text;
        CheckBox isAnswerCheckBox;
        ImageButton removeOptionBtn;

        public QuestionOptionViewHolder(@NonNull View itemView) {
            super(itemView);
            option_text = itemView.findViewById(R.id.option_text);
            isAnswerCheckBox = itemView.findViewById(R.id.isAnswerCheckBox);
            removeOptionBtn = itemView.findViewById(R.id.removeOptionBtn);

            isAnswerCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionOptionClickListener.onMarkCorrectClick(v, currentOption);
                }
            });

            removeOptionBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionOptionClickListener.onDeleteClick(v, currentOption);
                }
            });
        }
    }
}
