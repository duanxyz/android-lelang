package com.example.lelangonline.di.main.fragment;


import com.example.lelangonline.di.main.fragment.auction.AuctionModule;
import com.example.lelangonline.di.main.fragment.auction.AuctionScope;
import com.example.lelangonline.di.main.fragment.barang.BarangModule;
import com.example.lelangonline.di.main.fragment.barang.BarangScope;
import com.example.lelangonline.di.main.fragment.home.HomeModule;
import com.example.lelangonline.di.main.fragment.home.HomeScope;
import com.example.lelangonline.di.main.fragment.profile.ProfileModule;
import com.example.lelangonline.di.main.fragment.profile.ProfileScope;
import com.example.lelangonline.ui.auction.AuctionFragment;
import com.example.lelangonline.ui.barang.BarangFragment;
import com.example.lelangonline.ui.home.HomeFragment;
import com.example.lelangonline.ui.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentMainModule {


    @HomeScope
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment homeFragment();

    @BarangScope
    @ContributesAndroidInjector(modules = BarangModule.class)
    abstract BarangFragment barangFragment();

    @AuctionScope
    @ContributesAndroidInjector(modules = AuctionModule.class)
    abstract AuctionFragment auctionFragment();

    @ProfileScope
    @ContributesAndroidInjector(modules = ProfileModule.class)
    abstract ProfileFragment profileFragment();
}
