package com.example.lelangonline.di;

import androidx.lifecycle.ViewModel;


import com.example.lelangonline.ui.details.DetailsViewModel;
import com.example.lelangonline.ui.login.LoginViewModel;
import com.example.lelangonline.ui.profile.detail.ProfileDetailViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ActivityViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel.class)
    abstract ViewModel bindDetailsViewModel(DetailsViewModel detailsViewModel);

}
