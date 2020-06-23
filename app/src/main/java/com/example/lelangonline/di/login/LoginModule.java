package com.example.lelangonline.di.login;

import android.content.SharedPreferences;

import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.network.main.MainApi;
import com.example.lelangonline.ui.login.LoginRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class LoginModule {
//    @LoginScope
//    @Provides
//    @Named("LoginDisposable")
//    static CompositeDisposable provideDisposable() {
//        return new CompositeDisposable();
//    }

    @Provides
    @LoginScope
    @Named("croppedRequestOption")
    static RequestOptions provideNonCircleRequestOptions() {
        return RequestOptions.centerInsideTransform();
    }

    @Provides
    @LoginScope
    static LoginRepository provideLoginRepository(MainApi mainApi, CompositeDisposable disposable, SharedPreferences.Editor editor){
        return new LoginRepository(mainApi,disposable, editor);
    }
}
