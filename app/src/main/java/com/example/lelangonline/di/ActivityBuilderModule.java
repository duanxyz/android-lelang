package com.example.lelangonline.di;

import com.example.lelangonline.di.login.LoginModule;
import com.example.lelangonline.di.login.LoginScope;
import com.example.lelangonline.di.main.MainModule;
import com.example.lelangonline.di.main.MainScope;
import com.example.lelangonline.di.main.fragment.FragmentMainModule;
import com.example.lelangonline.di.auctionDetail.AuctionDetailsModule;
import com.example.lelangonline.di.auctionDetail.AuctionDetailsScope;
import com.example.lelangonline.di.onboarding.OnboardingModule;
import com.example.lelangonline.di.onboarding.OnboardingScope;
import com.example.lelangonline.di.splash.SplashModule;
import com.example.lelangonline.di.splash.SplashScope;
import com.example.lelangonline.ui.MainActivity;
import com.example.lelangonline.ui.auction.auctionDetail.AuctionActivity;
import com.example.lelangonline.ui.details.DetailsActivity;
import com.example.lelangonline.ui.login.LoginActivity;
import com.example.lelangonline.ui.onboarding.OnboardingActivity;
import com.example.lelangonline.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector(modules = {
            MainModule.class,
            FragmentMainModule.class,

    })
    abstract MainActivity mainActivityInject();

    @SplashScope
    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract SplashActivity splashActivity();

    @OnboardingScope
    @ContributesAndroidInjector(modules = OnboardingModule.class)
    abstract OnboardingActivity onboardingActivity();

    @LoginScope
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivity();


    @ContributesAndroidInjector
    abstract DetailsActivity detailsActivity();

    @AuctionDetailsScope
    @ContributesAndroidInjector(modules = AuctionDetailsModule.class)
    abstract AuctionActivity auctionActivity();



}
