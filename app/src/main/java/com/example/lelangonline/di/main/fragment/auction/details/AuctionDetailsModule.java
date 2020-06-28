package com.example.lelangonline.di.main.fragment.auction.details;

import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.database.saved.SavedDao;
import com.example.lelangonline.network.main.MainApi;
import com.example.lelangonline.ui.auction.auctionDetail.AuctionDetailRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class AuctionDetailsModule {
    @Provides
    @AuctionDetailsScope
    @Named("croppedRequestOption")
    static RequestOptions provideNonCircleRequestOptions() {
        return RequestOptions.centerInsideTransform();
    }

    @Provides
    @AuctionDetailsScope
    static AuctionDetailRepository provideAuctionDetailRepository(SavedDao savedDao, CompositeDisposable disposable, MainApi mainApi){
        return new AuctionDetailRepository(savedDao, disposable, mainApi);
    }
}
