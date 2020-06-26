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
    private MutableLiveData<BarangDataSource> mutableLiveData;

    @Inject
    public BarangDataSourceFactory(MainRepository mainRepository, CompositeDisposable disposable) {
        this.mainRepository = mainRepository;
        this.disposable = disposable;
        this.category = "semua";
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer ,DataItem> create() {
        BarangDataSource dataSource = new BarangDataSource(disposable, mainRepository, category);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<BarangDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setCategory(String category) {
        this.category = category;
    }
//    private MainApi mainApi;
//    private CompositeDisposable disposable;
//    private String category;
//    private  MainRepository mainRepository;
//    private MutableLiveData<BarangDataSource> mutableLiveData;
//
//    public BarangDataSourceFactory(MainApi mainApi, CompositeDisposable disposable) {
//        this.mainApi = mainApi;
//        this.disposable = disposable;
//        this.category = "";
//        this.mainRepository = mainRepository;
//        mutableLiveData = new MutableLiveData<>();
//    }
//
//    public MutableLiveData<BarangDataSource> getMutableLiveData() {
//        return mutableLiveData;
//    }
//
//    @Override
//    public DataSource<Integer, DataItem> create() {
//        Log.d("MMMMMM", "create: " );
//        BarangDataSource dataSource = new BarangDataSource(disposable, mainApi,category);
//        mutableLiveData.postValue(dataSource);
//        return dataSource;
//    }
//
//    public void setCategory(String category){
//        this.category = category;
//    }

}
