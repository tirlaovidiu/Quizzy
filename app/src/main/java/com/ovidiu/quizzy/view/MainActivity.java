package com.ovidiu.quizzy.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.ovidiu.quizzy.R;
import com.ovidiu.quizzy.databinding.ActivityMainBinding;
import com.ovidiu.quizzy.db.dao.QuizDao;
import com.ovidiu.quizzy.model.Answer;
import com.ovidiu.quizzy.model.ContentType;
import com.ovidiu.quizzy.model.Question;
import com.ovidiu.quizzy.model.Quiz;
import com.ovidiu.quizzy.adapter.QuizAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements QuizAdapter.QuizzesAdapterListener {
    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private QuizAdapter recyclerAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!Fresco.hasBeenInitialized())
            Fresco.initialize(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initRecyclerView();

    }

    private void initRecyclerView() {
        recyclerView = binding.quizRecyclerView;
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);
        recyclerAdapter = new QuizAdapter(getQuizzes(), this);
        recyclerView.setAdapter(recyclerAdapter);
    }


    private List<Quiz> getQuizzes() {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Quiz quiz = new Quiz();
            quiz.setId(String.valueOf(i));
            quiz.setName("Rate These Female Superheroes And We’ll Guess Your Favorite One");
            if (i % 2 == 0)
                quiz.setBackgroundUrl("https://static3.thequizimages.com/wordpress/wp-content/uploads/2018/06/Wonder-Woman-CREDIT-Buddha-Jones-1.jpg\n");
            else if (i % 3 == 0)
                quiz.setBackgroundUrl("https://images.pexels.com/photos/1405773/pexels-photo-1405773.jpeg");
            else if (i % 5 == 0)
                quiz.setBackgroundUrl("https://images.pexels.com/photos/1438084/pexels-photo-1438084.jpeg");
            else if (i % 7 == 0)
                quiz.setBackgroundUrl("https://images.pexels.com/photos/1391404/pexels-photo-1391404.jpeg");
            else
                quiz.setBackgroundUrl("https://wallpapercave.com/wp/wp2617529.jpg");

            String backgroundUrl = "https://static3.thequizimagesx.com/wordpress/wp-content/uploads/2018/06/Wonder-Woman-CREDIT-Buddha-Jones-1.jpg";

            Answer answer = new Answer("ID", ContentType.TEXT, backgroundUrl, "Answer title", 1);
            Answer answer2 = new Answer("ID2", ContentType.TEXT, backgroundUrl, "Answer title2", 2);

            ArrayList<Answer> answers = new ArrayList<>();
            answers.add(answer);
            answers.add(answer2);

            Question question = new Question("Question", answers);

            quiz.setQuestions(Collections.singletonList(question));
            quizzes.add(quiz);
        }
        return quizzes;
    }

    @Override
    public void onCardViewClicked(Quiz quiz) {
        startActivity(QuizActivity.newInstance(this, quiz.getId()));
    }

    @Override
    public void onShareClicked(Quiz quiz) {
        Toast.makeText(this, "Share clicked", Toast.LENGTH_SHORT).show();
    }
}
