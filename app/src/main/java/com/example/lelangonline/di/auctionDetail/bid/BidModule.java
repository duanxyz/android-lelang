package com.example.lelangonline.di.auctionDetail.bid;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.di.ViewModelKey;
import com.example.lelangonline.paging.auction.AuctionDataSourceFactory;
import com.example.lelangonline.ui.auction.auctionDetail.AuctionDetailRepository;
import com.example.lelangonline.ui.auction.auctionDetail.bid.BidAdapter;
import com.example.lelangonline.ui.auction.auctionDetail.bid.BidViewModel;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class BidModule {
//    @BidScope
//    @Provides
//    static LinearLayoutManager provideVerticalLayoutManager(Application application) {
//        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
//    }
//
//    @Provides
//    @BidScope
//    static AuctionDataSourceFactory provideDataSourceFactory(CompositeDisposable disposable , AuctionDetailRepository repository) {
//        return new AuctionDataSourceFactory(repository, disposable);
//    }
//
//
//    @BidScope
//    @Provides
//    static BidAdapter provideHomeAdapter() {
//        return new BidAdapter();
//    }

    @Binds
    @IntoMap
    @BidScope
    @ViewModelKey(BidViewModel.class)
    abstract ViewModel bindBidViewModel(BidViewModel bidViewModel);
}
