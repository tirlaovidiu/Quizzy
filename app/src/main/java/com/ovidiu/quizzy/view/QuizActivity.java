package com.ovidiu.quizzy.view;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ovidiu.quizzy.R;
import com.ovidiu.quizzy.databinding.QuizActivityBinding;
import com.ovidiu.quizzy.adapter.QuestionSlidePagerAdapter;
import com.ovidiu.quizzy.db.dao.QuizDao;
import com.ovidiu.quizzy.di.AppInjector;
import com.ovidiu.quizzy.utils.SwipeDirection;
import com.ovidiu.quizzy.view.ui.quiz.QuestionFragment;
import com.ovidiu.quizzy.view.ui.quiz.QuizViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class QuizActivity extends AppCompatActivity implements HasActivityInjector, HasSupportFragmentInjector, QuestionFragment.QuestionFragmentListener {

    private static final String QUIZ_UUID = "quiz_id";

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidFragmentInjector;
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private QuizActivityBinding binding;
    private PagerAdapter pagerAdapter;
    private QuizViewModel quizViewModel;
    private String quizId;

    public static Intent newInstance(Context context, String quizId) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(QUIZ_UUID, quizId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        quizId = getIntent().getStringExtra(QUIZ_UUID);

        if (quizId == null) {
            Toast.makeText(this, "Something went wrong !", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        binding = DataBindingUtil.setContentView(this, R.layout.quiz_activity);

        quizViewModel = ViewModelProviders.of(this, viewModelFactory).get(QuizViewModel.class);
        quizViewModel.init(quizId);

        initViewPager();
    }

    private void initViewPager() {
        pagerAdapter = new QuestionSlidePagerAdapter(getSupportFragmentManager(), quizViewModel.getQuiz().getQuestions());
        binding.viewPager.setAdapter(pagerAdapter);
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (positionOffset > 0 && quizViewModel.getResponse().get(position) != null) {
//                    binding.viewPager.setAllowedSwipeDirection(SwipeDirection.ALL);
//                } else if (positionOffset > 0) {
//                    binding.viewPager.setAllowedSwipeDirection(SwipeDirection.LEFT);
//                } else if (positionOffset < 0) {
//                    binding.viewPager.setAllowedSwipeDirection(SwipeDirection.ALL);
//                }
            }

            @Override
            public void onPageSelected(final int position) {
                if (position > 0 && quizViewModel.getResponse().get(position - 1) == null) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuizActivity.this)
                            .setMessage("Please answer the previous question first.")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    binding.viewPager.setCurrentItem(position - 1, true);
                                }
                            });
                    alertDialog.show();
                }
                if (quizViewModel.getResponse().get(position) != null) {
                    binding.viewPager.setAllowedSwipeDirection(SwipeDirection.ALL);
                } else {
                    binding.viewPager.setAllowedSwipeDirection(SwipeDirection.LEFT);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        binding.viewPager.setAllowedSwipeDirection(SwipeDirection.LEFT);

        binding.stepperIndicator.setupWithViewPager(binding.viewPager);

        LinearLayout tabStrip = ((LinearLayout) binding.stepperIndicator.getChildAt(0));
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            final int finalI = i;
            tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (quizViewModel.getResponse().get(finalI - 1) == null && finalI > 0)
                        return true;
                    binding.viewPager.setCurrentItem(finalI, true);
                    return true;
                }
            });
        }
        binding.stepperIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LinearLayout tabStrip = ((LinearLayout) binding.stepperIndicator.getChildAt(0));
                tabStrip.getChildAt(tab.getPosition()).setBackground(getResources().getDrawable(R.drawable.selected_dot));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LinearLayout tabStrip = ((LinearLayout) binding.stepperIndicator.getChildAt(0));
                if (quizViewModel.getResponse().get(tab.getPosition()) != null) {
                    tabStrip.getChildAt(tab.getPosition()).setBackground(getResources().getDrawable(R.drawable.completed_dot));
                } else {
                    tabStrip.getChildAt(tab.getPosition()).setBackground(getResources().getDrawable(R.drawable.locked_dot));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onQuestionAnswered(int questionId) {
        binding.viewPager.setCurrentItem(quizViewModel.getResponse().keyAt(quizViewModel.getResponse().size() - 1) + 1);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidFragmentInjector;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
