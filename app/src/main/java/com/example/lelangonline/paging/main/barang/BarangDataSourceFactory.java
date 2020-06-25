package com.example.lelangonline.paging.main.barang;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.lelangonline.models.DataItem;
import com.example.lelangonline.network.main.MainApi;
import com.example.lelangonline.ui.MainRepository;

import io.reactivex.disposables.CompositeDisposable;

public class BarangDataSourceFactory extends DataSource.Factory {

    private MainApi mainApi;
    private CompositeDisposable disposable;
    private String category;
    private  MainRepository mainRepository;
    private MutableLiveData<BarangDataSource> mutableLiveData;

    public BarangDataSourceFactory(MainApi mainApi, CompositeDisposable disposable, MainRepository mainRepository) {
        this.mainApi = mainApi;
        this.disposable = disposable;
        this.category = "kendaraan";
        this.mainRepository = mainRepository;
        mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<BarangDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

    @Override
    public DataSource<Integer, DataItem> create() {
        Log.d("MMMMMM", "create: " );
        BarangDataSource dataSource = new BarangDataSource(disposable, mainApi,category, mainRepository);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public void setCategory(String category){
        this.category = category;
    }

}
