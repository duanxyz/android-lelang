package com.example.lelangonline.di.auctionDetail.bid;

import com.example.lelangonline.ui.auction.auctionDetail.bid.BidFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBidModule {
    @BidScope
    @ContributesAndroidInjector(modules = BidModule.class)
    abstract BidFragment bidFragment();
}
