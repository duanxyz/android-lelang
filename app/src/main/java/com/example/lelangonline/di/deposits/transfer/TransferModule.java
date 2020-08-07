package com.example.lelangonline.di.deposits.transfer;

import androidx.lifecycle.ViewModel;

import com.example.lelangonline.di.ViewModelKey;
import com.example.lelangonline.ui.deposit.transfer.TransferViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class TransferModule {
    @Binds
    @IntoMap
    @ViewModelKey(TransferViewModel.class)
    abstract ViewModel bindTransferViewModel(TransferViewModel transferViewModel);

}
