package com.ovidiu.quizzy.view.ui.quiz;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ovidiu.quizzy.R;
import com.ovidiu.quizzy.adapter.AnswerAdapter;
import com.ovidiu.quizzy.databinding.QuestionFragmentBinding;
import com.ovidiu.quizzy.di.Injectable;
import com.ovidiu.quizzy.model.Answer;
import com.ovidiu.quizzy.model.Question;

import javax.inject.Inject;

public class QuestionFragment extends Fragment implements AnswerAdapter.AnswerAdapterListener, Injectable {

    private final static String QUESTION_UUID = "question_id";

    private QuestionFragmentListener questionFragmentListener;

    private QuestionFragmentBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private QuizViewModel quizViewModel;
    private int questionId;
    private Question question;

    private AnswerAdapter answerAdapter;

    public static QuestionFragment newInstance(int questionId) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(QUESTION_UUID, questionId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.question_fragment, container, false);
        View view = binding.getRoot();

        Bundle bundle = this.getArguments();
        if (bundle != null)
            questionId = bundle.getInt(QUESTION_UUID);
        else
            System.out.println("LOG");
        //TODO implement logger!!
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() == null)//TODO implement logger!!
            System.out.println("LOG");

        quizViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(QuizViewModel.class);
        question = quizViewModel.getQuiz().getQuestions().get(questionId);
        binding.setQuestion(question);
        initRecyclerView();
    }

    private void initRecyclerView() {
        binding.questionAnswersRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        answerAdapter = new AnswerAdapter(question.getAnswers(), quizViewModel.getResponse().get(questionId), this, getContext());
        binding.questionAnswersRecycler.setAdapter(answerAdapter);
    }

    @Override
    public void onAnswerSelected(Answer answer) {
        quizViewModel.getScore().put(questionId, answer.getPoints());
        quizViewModel.getResponse().put(questionId, answer.getId());
        answerAdapter.notifyDataSetChanged();
        questionFragmentListener.onQuestionAnswered(questionId);
    }

    public interface QuestionFragmentListener {
        void onQuestionAnswered(int questionId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            questionFragmentListener = (QuestionFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement QuestionFragmentListener");
        }

    }
}
