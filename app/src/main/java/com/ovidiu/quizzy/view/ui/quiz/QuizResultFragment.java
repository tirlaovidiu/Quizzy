package com.ovidiu.quizzy.view.ui.quiz;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.ovidiu.quizzy.R;
import com.ovidiu.quizzy.databinding.QuizResultFragmentBinding;
import com.ovidiu.quizzy.di.Injectable;
import com.ovidiu.quizzy.model.Result;
import com.vungle.mediation.VungleAdapter;
import com.vungle.mediation.VungleExtrasBuilder;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;

import java.util.Map;

import javax.inject.Inject;

import static com.ovidiu.quizzy.utils.Constants.REWARD_VIDEO_AD_KEY;
import static java.lang.Thread.sleep;

public class QuizResultFragment extends Fragment implements Injectable, RewardedVideoAdListener {

    private RewardedVideoAd videoAd;

    private QuizResultFragmentBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private QuizViewModel quizViewModel;

    private boolean isFragmentCreated = false;
    private int finalScore;
    private Result result;

    public static QuizResultFragment newInstance() {
        return new QuizResultFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_result_fragment, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() == null)//TODO implement logger!!
            System.out.println("LOG");

        quizViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(QuizViewModel.class);

        updateUiWaiting();
        if (getUserVisibleHint())
            updateUiResults();
        videoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        videoAd.setRewardedVideoAdListener(this);
        binding.loadingProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRewardedVideoAd();
            }
        });
        isFragmentCreated = true;

    }

    private void loadRewardedVideoAd() {
        String[] placements = new String[1];
        placements[0] = "DEFAULT-2584338";
        Bundle extras = new VungleExtrasBuilder(placements).build();
        videoAd.loadAd(REWARD_VIDEO_AD_KEY, new AdRequest.Builder()
                .addNetworkExtrasBundle(VungleAdapter.class, extras)             // Rewarded video.
                .build());
        if (videoAd.isLoaded()) {
            binding.loadingProgressText.setText("Loaded");
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            videoAd.show();
        }
    }

    private void updateUiWaiting() {
        binding.loadingProgressLinear.setVisibility(View.VISIBLE);
        binding.resultLinear.setVisibility(View.GONE);
    }

    private void updateUiResults() {
        loadRewardedVideoAd();
        getResults();
    }

    private void showResults() {
        binding.loadingProgressLinear.setVisibility(View.GONE);

        for (Result result : quizViewModel.getQuiz().getResults()) {
            if (result.getPoints() == finalScore) {
                this.result = result;
            }
        }
        if (result == null) {
            result = quizViewModel.getQuiz().getResults().get(0);
        }
        binding.setResult(result);

        binding.resultImage.setImageURI(result.getImageUrl());

        binding.resultLinear.setVisibility(View.VISIBLE);
    }

    private void getResults() {

        finalScore = 0;
        for (Map.Entry<Integer, Integer> entry : quizViewModel.getScore().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            finalScore += entry.getValue();
        }

        Toast.makeText(getActivity(), "Your score is : " + finalScore, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isFragmentCreated)
            updateUiResults();
    }

    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(getContext(), "onRewarded! currency: " + reward.getType() + "  amount: " + reward.getAmount(), Toast.LENGTH_SHORT).show();
        // Reward the user.
        showResults();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(getContext(), "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(getContext(), "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(getContext(), "onRewardedVideoAdFailedToLoad " + errorCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(getContext(), "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
        videoAd.show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(getContext(), "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(getContext(), "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
        Toast.makeText(getContext(), "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
        showResults();
    }

    @Override
    public void onResume() {
        videoAd.resume(getContext());
        super.onResume();
    }

    @Override
    public void onPause() {
        videoAd.pause(getContext());
        super.onPause();
    }

    @Override
    public void onDestroy() {
        videoAd.destroy(getContext());
        super.onDestroy();
    }
}
