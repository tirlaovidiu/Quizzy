package com.ovidiu.quizzy;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.gms.ads.MobileAds;
import com.ovidiu.quizzy.di.AppInjector;
import com.vungle.warren.InitCallback;
import com.vungle.warren.Vungle;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MVVMApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidFragmentInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.init(this);
        Fresco.initialize(this);
        MobileAds.initialize(this, "ca-app-pub-9905148751481356~7283989342");
        Vungle.init("5bd89cc626179500109c4a13", this, new InitCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(MVVMApplication.this, "OnSuccess", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(MVVMApplication.this, "onError " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAutoCacheAdAvailable(String s) {
                Toast.makeText(MVVMApplication.this, s, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidFragmentInjector;
    }
}
