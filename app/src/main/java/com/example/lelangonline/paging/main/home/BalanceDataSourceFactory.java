package com.example.lelangonline.paging.main.home;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.lelangonline.models.balance.Balance;
import com.example.lelangonline.ui.MainRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BalanceDataSourceFactory extends DataSource.Factory{
    private MainRepository mainRepository;
    private CompositeDisposable disposable;
    private MutableLiveData<BalanceDataSource> mutableLiveData;

    @Inject
    public BalanceDataSourceFactory(MainRepository mainRepository, CompositeDisposable disposable) {
        this.mainRepository = mainRepository;
        this.disposable = disposable;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer , Balance> create() {
        BalanceDataSource dataSource = new BalanceDataSource(disposable, mainRepository);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<BalanceDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

}
