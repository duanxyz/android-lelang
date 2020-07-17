package com.example.lelangonline.di.profile.detail;

import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.database.NewsDao;
import com.example.lelangonline.database.saved.SavedDao;
import com.example.lelangonline.di.ViewModelKey;
import com.example.lelangonline.network.main.MainApi;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.ui.profile.detail.ProfileDetailViewModel;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class ProfileDetailModule {
    @Provides
    @ProfileDetailScope
    @Named("croppedRequestOption")
    static RequestOptions provideNonCircleRequestOptions() {
        return RequestOptions.centerInsideTransform();
    }

    @Binds
    @IntoMap
    @ViewModelKey(ProfileDetailViewModel.class)
    abstract ViewModel bindProfileDetailView(ProfileDetailViewModel profileDetailViewModel);


//    @Provides
//    @ProfileDetailScope
//    static MainRepository mainRepositoryInject(MainApi mainApi, CompositeDisposable disposable, NewsDao newsDao, SharedPreferences preferences) {
//        return new MainRepository(mainApi, disposable, newsDao, preferences );
//    }
}
