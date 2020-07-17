package com.example.lelangonline.di.withdraw;

import androidx.lifecycle.ViewModel;

import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.di.ViewModelKey;
import com.example.lelangonline.ui.withdraw.WithdrawViewModel;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public abstract class WithdrawModule {
    @Provides
    @WithdrawScope
    @Named("croppedRequestOption")
    static RequestOptions provideNonCircleRequestOptions() {
        return RequestOptions.centerInsideTransform();
    }

    @Binds
    @IntoMap
    @ViewModelKey(WithdrawViewModel.class)
    abstract ViewModel bindWithdrawViewModel(WithdrawViewModel withdrawViewModel);
}
