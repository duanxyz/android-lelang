package com.example.lelangonline.di.main.fragment.barang;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.di.ViewModelKey;
import com.example.lelangonline.network.main.MainApi;
import com.example.lelangonline.paging.main.barang.BarangDataSourceFactory;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.ui.barang.BarangItemAdapter;
import com.example.lelangonline.ui.barang.BarangMainAdapter;
import com.example.lelangonline.ui.barang.BarangViewModel;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class BarangModule {
    @Named("vertical")
    @BarangScope
    @Provides
    static LinearLayoutManager provideVerticalLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
    }

    @Named("horizontal")
    @BarangScope
    @Provides
    static LinearLayoutManager provideLinearLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.HORIZONTAL, false);
    }

    @Provides
    @BarangScope
    static BarangDataSourceFactory provideBarangDataSourceFactory(CompositeDisposable disposable , MainApi mainApi, MainRepository mainRepository) {
        return new BarangDataSourceFactory(mainApi, disposable, mainRepository);
    }

    @BarangScope
    @Provides
    static BarangMainAdapter provideMainAdapter(RequestManager requestManager, @Named("defaultRequestOption") RequestOptions requestOptions) {
        return new BarangMainAdapter(requestManager, requestOptions);
    }

    @BarangScope
    @Provides
    static BarangItemAdapter provideBarangAdapter() {
        return new BarangItemAdapter();
    }



    @Binds
    @IntoMap
    @BarangScope
    @ViewModelKey(BarangViewModel.class)
    abstract ViewModel bindHomeViewModel(BarangViewModel BarangViewModel);
}
