package com.example.lelangonline.ui.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.lelangonline.ui.MainActivity;
import com.example.lelangonline.ui.onboarding.OnboardingActivity;
import com.example.lelangonline.utils.Constants;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

public class SplashActivity extends DaggerAppCompatActivity {
    @Inject
    SharedPreferences preferences;

    @Inject
    @Named("splashDisposable")
    CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean session = preferences.getBoolean(Constants.SESSION_PREFS, false);
        if (session){
            startActivity(new Intent(this.getApplicationContext(), MainActivity.class));
            finish();
        }else {
            startActivity(new Intent(this.getApplicationContext(), OnboardingActivity.class));
            finish();
        }
    }
}
