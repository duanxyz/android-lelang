package com.example.lelangonline.di.main.fragment.home;

import androidx.lifecycle.ViewModel;

import com.example.lelangonline.di.ViewModelKey;
import com.example.lelangonline.ui.main.home.HomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class HomeModule {


//    @HomeScope
//    @Provides
//    static LinearLayoutManager provideVerticalLayoutManager(Application application) {
//        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
//    }

//    @Provides
//    @HomeScope
//    static HomeDataSourceFactory provideDataSourceFactory(CompositeDisposable disposable , MainRepository repository) {
//        return new HomeDataSourceFactory(repository, disposable);
//    }
//
//
//    @HomeScope
//    @Provides
//    static HomeAdapter provideHomeAdapter(RequestManager requestManager, @Named("defaultRequestOption") RequestOptions requestOptions) {
//        return new HomeAdapter(requestManager,requestOptions);
//    }

    @Binds
    @IntoMap
    @HomeScope
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);
}
