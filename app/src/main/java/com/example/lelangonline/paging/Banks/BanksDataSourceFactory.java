package com.example.lelangonline.paging.Banks;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.lelangonline.models.banks.Banks;
import com.example.lelangonline.ui.MainRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BanksDataSourceFactory extends DataSource.Factory {
    private MainRepository mainRepository;
    private CompositeDisposable disposable;
    private MutableLiveData<BanksDataSource> mutableLiveData;

    @Inject
    public BanksDataSourceFactory(MainRepository repository, CompositeDisposable disposable) {
        this.mainRepository = repository;
        this.disposable = disposable;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer, Banks> create() {
        BanksDataSource dataSource = new BanksDataSource(disposable, mainRepository);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<BanksDataSource> getMutableLiveData(){
        return mutableLiveData;
    }
}
