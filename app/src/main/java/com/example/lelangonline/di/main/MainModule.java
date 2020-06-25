package com.example.lelangonline.di.main;


import android.content.SharedPreferences;

import androidx.paging.PagedList;

import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.database.NewsDao;
import com.example.lelangonline.network.main.MainApi;
import com.example.lelangonline.ui.MainRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MainModule {

    @Provides
    @MainScope
    @Named("defaultRequestOption")
    static RequestOptions provideNonCircleRequestOptions() {
        return RequestOptions.centerInsideTransform();
    }

    @Provides
    @MainScope
    static PagedList.Config config(){
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build();
    }

    @MainScope
    @Provides
    static MainRepository mainRepositoryInject(MainApi mainApi, CompositeDisposable disposable, NewsDao newsDao, SharedPreferences preferences) {
        return new MainRepository(mainApi, disposable, newsDao, preferences );
    }

}
