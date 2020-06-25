package com.example.lelangonline.di.main.fragment;


import com.example.lelangonline.di.main.fragment.barang.BarangModule;
import com.example.lelangonline.di.main.fragment.barang.BarangScope;
import com.example.lelangonline.di.main.fragment.home.HomeModule;
import com.example.lelangonline.di.main.fragment.home.HomeScope;
import com.example.lelangonline.ui.barang.BarangFragment;
import com.example.lelangonline.ui.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentMainModule {


    @HomeScope
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment homeFragment();

//    @SearchScope
//    @ContributesAndroidInjector(modules = SearchModule.class)
//    abstract SearchFragment searchFragment();
//
//
    @BarangScope
    @ContributesAndroidInjector(modules = BarangModule.class)
    abstract BarangFragment barangFragment();

//    @SavedScope
//    @ContributesAndroidInjector(modules = SavedModule.class)
//    abstract SavedFragment savedFragment();
}
