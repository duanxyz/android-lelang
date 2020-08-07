package com.example.lelangonline.di;

import com.example.lelangonline.di.deposits.DepositModule;
import com.example.lelangonline.di.deposits.DepositScope;
import com.example.lelangonline.di.deposits.transfer.TransferModule;
import com.example.lelangonline.di.deposits.transfer.TransferScope;
import com.example.lelangonline.di.login.LoginModule;
import com.example.lelangonline.di.login.LoginScope;
import com.example.lelangonline.di.main.MainModule;
import com.example.lelangonline.di.main.MainScope;
import com.example.lelangonline.di.main.fragment.FragmentMainModule;
import com.example.lelangonline.di.auctionDetail.AuctionDetailsModule;
import com.example.lelangonline.di.auctionDetail.AuctionDetailsScope;
import com.example.lelangonline.di.onboarding.OnboardingModule;
import com.example.lelangonline.di.onboarding.OnboardingScope;
import com.example.lelangonline.di.profile.detail.ProfileDetailModule;
import com.example.lelangonline.di.profile.detail.ProfileDetailScope;
import com.example.lelangonline.di.splash.SplashModule;
import com.example.lelangonline.di.splash.SplashScope;
import com.example.lelangonline.di.withdraw.WithdrawModule;
import com.example.lelangonline.di.withdraw.WithdrawScope;
import com.example.lelangonline.ui.MainActivity;
import com.example.lelangonline.ui.auctionDetail.AuctionActivity;
import com.example.lelangonline.ui.deposit.DepositActivity;
import com.example.lelangonline.ui.deposit.transfer.TransferActivity;
import com.example.lelangonline.ui.details.DetailsActivity;
import com.example.lelangonline.ui.login.LoginActivity;
import com.example.lelangonline.ui.onboarding.OnboardingActivity;
import com.example.lelangonline.ui.profile.detail.ProfileActivity;
import com.example.lelangonline.ui.splash.SplashActivity;
import com.example.lelangonline.ui.withdraw.WithdrawActivity;

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

    @ProfileDetailScope
    @ContributesAndroidInjector(modules = ProfileDetailModule.class)
    abstract ProfileActivity profileActivity();

    @WithdrawScope
    @ContributesAndroidInjector(modules = WithdrawModule.class)
    abstract WithdrawActivity withdrawActivity();

    @DepositScope
    @ContributesAndroidInjector(modules = DepositModule.class)
    abstract DepositActivity depositActivity();

    @TransferScope
    @ContributesAndroidInjector(modules = TransferModule.class)
    abstract TransferActivity transferActivity();



}
