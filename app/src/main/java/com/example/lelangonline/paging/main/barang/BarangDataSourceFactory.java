package com.example.lelangonline.paging.main.barang;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.lelangonline.models.DataItem;
import com.example.lelangonline.network.main.MainApi;
import com.example.lelangonline.ui.MainRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BarangDataSourceFactory extends DataSource.Factory {


    private MainRepository mainRepository;
    private CompositeDisposable disposable;
    private String category;
    private String search;
    private String date;
    private MutableLiveData<BarangDataSource> mutableLiveData;

    @Inject
    public BarangDataSourceFactory(MainRepository mainRepository, CompositeDisposable disposable) {
        this.mainRepository = mainRepository;
        this.disposable = disposable;
        this.category = "semua";
        this.search = "";
        this.date = "";
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer ,DataItem> create() {
        BarangDataSource dataSource = new BarangDataSource(disposable, mainRepository, category, search, date);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<BarangDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setDate(String currentDateandTime) {
        this.date = currentDateandTime;
    }
}
