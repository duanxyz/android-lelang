package com.example.lelangonline.di.main.fragment.home;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lelangonline.di.ViewModelKey;
import com.example.lelangonline.paging.main.home.BalanceDataSourceFactory;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.ui.main.home.HomeAdapter;
import com.example.lelangonline.ui.main.home.HomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class HomeModule {

    @HomeScope
    @Provides
    static LinearLayoutManager provideVerticalLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
    }

    @HomeScope
    @Provides
    static BalanceDataSourceFactory provideDataSourceFactory(CompositeDisposable disposable , MainRepository repository) {
        return new BalanceDataSourceFactory(repository, disposable);
    }

    @HomeScope
    @Provides
    static HomeAdapter provideHomeAdapter() {
        return new HomeAdapter();
    }

    @Binds
    @IntoMap
    @HomeScope
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);
}
