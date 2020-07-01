package com.example.lelangonline.di.auctionDetail;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.database.NewsDao;
import com.example.lelangonline.database.saved.SavedDao;
import com.example.lelangonline.di.ViewModelKey;
import com.example.lelangonline.network.main.MainApi;
import com.example.lelangonline.paging.auction.AuctionDataSourceFactory;
import com.example.lelangonline.ui.auction.auctionDetail.AuctionDetailRepository;
import com.example.lelangonline.ui.auction.auctionDetail.AuctionDetailViewModel;
import com.example.lelangonline.ui.auction.auctionDetail.bid.BidAdapter;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class AuctionDetailsModule {

    @AuctionDetailsScope
    @Provides
    static LinearLayoutManager provideVerticalLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    @AuctionDetailsScope
    static AuctionDataSourceFactory provideDataSourceFactory(CompositeDisposable disposable , AuctionDetailRepository repository) {
        return new AuctionDataSourceFactory(repository, disposable);
    }


    @AuctionDetailsScope
    @Provides
    static BidAdapter provideAuctionAdapter() {
        return new BidAdapter();
    }

    @Provides
    @AuctionDetailsScope
    static PagedList.Config config(){
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build();
    }

    @Provides
    @AuctionDetailsScope
    static AuctionDetailRepository provideAuctionDetailRepository(SavedDao savedDao, CompositeDisposable disposable, MainApi mainApi,
                                                                  NewsDao newsDao, SharedPreferences preferences){
        return new AuctionDetailRepository(savedDao, disposable, mainApi, newsDao, preferences);
    }

    @Binds
    @IntoMap
    @ViewModelKey(AuctionDetailViewModel.class)
    abstract ViewModel bindAuctionDetailsViewModel(AuctionDetailViewModel auctionDetailViewModel);
}
