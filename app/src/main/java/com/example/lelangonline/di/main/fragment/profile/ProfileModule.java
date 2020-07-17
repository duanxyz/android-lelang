package com.example.lelangonline.di.main.fragment.profile;

import androidx.lifecycle.ViewModel;

import com.example.lelangonline.di.ViewModelKey;
import com.example.lelangonline.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ProfileModule {

    @Binds
    @IntoMap
    @ProfileScope
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);
}
