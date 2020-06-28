package com.example.lelangonline.di.main.fragment.auction;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.di.ViewModelKey;
import com.example.lelangonline.paging.main.barang.BarangDataSourceFactory;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.ui.auction.AuctionAdapter;
import com.example.lelangonline.ui.auction.AuctionViewModel;
import com.example.lelangonline.ui.barang.BarangAdapter;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class AuctionModule {

    @AuctionScope
    @Provides
    static LinearLayoutManager provideVerticalLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    @AuctionScope
    static BarangDataSourceFactory provideDataSourceFactory(CompositeDisposable disposable , MainRepository repository) {
        return new BarangDataSourceFactory(repository, disposable);
    }


    @AuctionScope
    @Provides
    static AuctionAdapter provideHomeAdapter(RequestManager requestManager, @Named("defaultRequestOption") RequestOptions requestOptions) {
        return new AuctionAdapter(requestManager,requestOptions);
    }

    @Binds
    @IntoMap
    @AuctionScope
    @ViewModelKey(AuctionViewModel.class)
    abstract ViewModel bindAuctionViewModel(AuctionViewModel auctionViewModel);
}
