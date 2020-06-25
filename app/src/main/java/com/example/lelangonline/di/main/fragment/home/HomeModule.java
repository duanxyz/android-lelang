package com.example.lelangonline.di.main.fragment.home;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.di.ViewModelKey;
import com.example.lelangonline.paging.main.home.HomeDataSourceFactory;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.ui.home.HomeAdapter;
import com.example.lelangonline.ui.home.HomeViewModel;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class HomeModule {


//    @HomeScope
//    @Provides
//    static LinearLayoutManager provideVerticalLayoutManager(Application application) {
//        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
//    }

    @Provides
    @HomeScope
    static HomeDataSourceFactory provideDataSourceFactory(CompositeDisposable disposable , MainRepository repository) {
        return new HomeDataSourceFactory(repository, disposable);
    }


    @HomeScope
    @Provides
    static HomeAdapter provideHomeAdapter(RequestManager requestManager, @Named("defaultRequestOption") RequestOptions requestOptions) {
        return new HomeAdapter(requestManager,requestOptions);
    }

    @Binds
    @IntoMap
    @HomeScope
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);
}
