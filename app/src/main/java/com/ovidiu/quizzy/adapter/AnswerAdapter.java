package com.ovidiu.quizzy.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ovidiu.quizzy.R;
import com.ovidiu.quizzy.databinding.AnswerListItemBinding;
import com.ovidiu.quizzy.model.Answer;
import com.ovidiu.quizzy.model.ContentType;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    private List<Answer> answerList;
    private String answerId;
    private LayoutInflater layoutInflater;
    private AnswerAdapterListener listener;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final AnswerListItemBinding binding;

        public ViewHolder(final AnswerListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    public AnswerAdapter(List<Answer> answerList, String answerId, AnswerAdapterListener listener, Context context) {
        this.answerList = answerList;
        this.answerId = answerId;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public AnswerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        AnswerListItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.answer_list_item, viewGroup, false);
        return new AnswerAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final AnswerAdapter.ViewHolder holder, final int position) {
        holder.binding.setAnswer(answerList.get(holder.getAdapterPosition()));
        holder.binding.answerCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerId = answerList.get(holder.getAdapterPosition()).getId();
                listener.onAnswerSelected(answerList.get(holder.getAdapterPosition()));
            }
        });

        if (holder.binding.getAnswer().getType() == ContentType.IMAGE_WITH_TEXT) {
            holder.binding.answerBackground.setImageURI(answerList.get(holder.getAdapterPosition()).getImageUrl());
        }

        if (holder.binding.getAnswer().getId().equals(answerId)) {
            holder.binding.answerBackground.getHierarchy().setOverlayImage(new ColorDrawable(context.getResources().getColor(R.color.cardview_selected_background)));
        } else {
            holder.binding.answerBackground.getHierarchy().setOverlayImage(null);
        }
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    public interface AnswerAdapterListener {
        void onAnswerSelected(Answer answer);
    }
}
