package com.example.lelangonline.di.main.fragment.barang;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.di.ViewModelKey;
import com.example.lelangonline.paging.main.barang.BarangDataSourceFactory;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.ui.main.barang.BarangAdapter;
import com.example.lelangonline.ui.main.barang.BarangViewModel;
import com.example.lelangonline.ui.main.barang.CategoryItemAdapter;

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
    static BarangDataSourceFactory provideDataSourceFactory(CompositeDisposable disposable , MainRepository repository) {
        return new BarangDataSourceFactory(repository, disposable);
    }


    @BarangScope
    @Provides
    static BarangAdapter provideHomeAdapter(RequestManager requestManager, @Named("defaultRequestOption") RequestOptions requestOptions) {
        return new BarangAdapter(requestManager,requestOptions);
    }

    @BarangScope
    @Provides
    static CategoryItemAdapter provideCategoryAdapter() {
        return new CategoryItemAdapter();
    }


    @Binds
    @IntoMap
    @BarangScope
    @ViewModelKey(BarangViewModel.class)
    abstract ViewModel bindBarangViewModel(BarangViewModel barangViewModel);
}
