package com.example.lelangonline.di.onboarding;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class OnboardingModule {
    @OnboardingScope
    @Provides
    @Named("onboardingDisposable")
    static CompositeDisposable provideDisposable() {
        return new CompositeDisposable();
    }
}
