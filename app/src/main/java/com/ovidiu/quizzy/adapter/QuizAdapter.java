package com.ovidiu.quizzy.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ovidiu.quizzy.R;
import com.ovidiu.quizzy.databinding.QuizListItemBinding;
import com.ovidiu.quizzy.model.Quiz;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {

    private List<Quiz> quizList;
    private LayoutInflater layoutInflater;
    private QuizzesAdapterListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final QuizListItemBinding binding;

        public ViewHolder(final QuizListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    public QuizAdapter(List<Quiz> quizList, QuizzesAdapterListener listener) {
        this.quizList = quizList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuizAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        QuizListItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.quiz_list_item, parent, false);
        return new QuizAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuizAdapter.ViewHolder holder, int position) {
        holder.binding.setQuiz(quizList.get(position));
        holder.binding.quizBackground.setImageURI(quizList.get(holder.getAdapterPosition()).getBackgroundUrl());

        holder.binding.quizCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCardViewClicked(quizList.get(holder.getAdapterPosition()));
                }
            }
        });
        holder.binding.quizActionBarShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onShareClicked(quizList.get(holder.getAdapterPosition()));
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public interface QuizzesAdapterListener {
        void onCardViewClicked(Quiz quiz);

        void onShareClicked(Quiz quiz);
    }

}
