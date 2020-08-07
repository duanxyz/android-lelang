package com.example.lelangonline.di.deposits;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.di.ViewModelKey;
import com.example.lelangonline.paging.Banks.BanksDataSourceFactory;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.ui.deposit.BankTransferAdapter;
import com.example.lelangonline.ui.deposit.DepositViewModel;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class DepositModule {

    @DepositScope
    @Provides
    static LinearLayoutManager provideVerticalLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
    }

    @DepositScope
    @Provides
    static BankTransferAdapter provideBankTransferAdapter(){
        return new BankTransferAdapter();
    }

//    @Provides
//    @DepositScope
//    @Named("croppedRequestOption")
//    static RequestOptions provideNonCircleRequestOptions() {
//        return RequestOptions.centerInsideTransform();
//    }

    @Provides
    @DepositScope
    static PagedList.Config config(){
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build();
    }

    @Provides
    @DepositScope
    static BanksDataSourceFactory provideDataSourceFactory(CompositeDisposable disposable , MainRepository repository) {
        return new BanksDataSourceFactory(repository, disposable);
    }

    @Binds
    @IntoMap
    @ViewModelKey(DepositViewModel.class)
    abstract ViewModel bindDepositViewModel(DepositViewModel depositViewModel);

}
