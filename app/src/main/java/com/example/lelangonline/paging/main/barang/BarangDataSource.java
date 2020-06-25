package com.example.lelangonline.paging.main.barang;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.lelangonline.models.DataItem;
import com.example.lelangonline.network.main.MainApi;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.utils.DataStatus;

import io.reactivex.disposables.CompositeDisposable;

public class BarangDataSource extends PageKeyedDataSource<Integer, DataItem> {

    private static final String TAG = "CategoryDataSource";
    private CompositeDisposable disposable;
    private MainApi mainApi;
    private String category;
    private MainRepository mainRepository;
    private MutableLiveData<DataStatus> mutableLiveData;

    public LiveData<DataStatus> getMutableLiveData() {
        return mutableLiveData;
    }

    public BarangDataSource(CompositeDisposable disposable, MainApi mainApi, String category, MainRepository mainRepository ) {
        this.disposable = disposable;
        this.mainApi = mainApi;
        mutableLiveData = new MutableLiveData<>();
        if(category.equalsIgnoreCase("world"))
            category = "general";
        this.category = category;
        this.mainRepository = mainRepository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, DataItem> callback) {
        mutableLiveData.postValue(DataStatus.LOADING);
        disposable.add(mainRepository.fetchFromApi(1, params.requestedLoadSize)
                .subscribe(data -> {
                            if (data.getData().isEmpty())
                                throw new NullPointerException();

                            callback.onResult(data.getData(), null, 2);
                            mutableLiveData.postValue(DataStatus.LOADED);
                            mainRepository.saveData(data);
                        }, throwable ->
                                disposable.add(mainRepository.fetchFromDB(10, 0)
                                        .subscribe(data -> {
                                            if (data.isEmpty())
                                                mutableLiveData.postValue(DataStatus.ERROR);
                                            else {
                                                mutableLiveData.postValue(DataStatus.LOADED);
                                                callback.onResult(data, null, 10);
                                            }
                                        }, error -> Log.d(TAG, "loadInitial: " + error)))
                ));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataItem> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataItem> callback) {
        disposable.add(
                mainRepository.fetchFromApi(params.key, params.requestedLoadSize)
                        .subscribe(data -> {
                                    callback.onResult(data.getData(), params.key + 1);
                                    mutableLiveData.postValue(DataStatus.LOADED);
                                    mainRepository.saveData(data);
                                }, throwable -> disposable.add(
                                mainRepository.fetchFromDB(10, params.key)
                                        .subscribe(data -> {
                                                    Log.d(TAG, "loadMMM: New Room " + data.size());
                                                    callback.onResult(data, params.key + 10);
                                                    mutableLiveData.postValue(DataStatus.LOADED);
                                                },
                                                throwable1 -> Log.d(TAG, "database  ERROR " + throwable1))
                                )

                        ));
    }
}
