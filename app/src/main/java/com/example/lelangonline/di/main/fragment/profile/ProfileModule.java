package com.example.lelangonline.di.main.fragment.profile;

import androidx.lifecycle.ViewModel;

import com.example.lelangonline.di.ViewModelKey;
import com.example.lelangonline.paging.MemberDataSourceFactory;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.ui.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class ProfileModule {

    @Binds
    @IntoMap
    @ProfileScope
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);
}
