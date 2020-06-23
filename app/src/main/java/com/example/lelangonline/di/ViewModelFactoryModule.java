package com.example.lelangonline.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.lelangonline.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);

}