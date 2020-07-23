package com.example.lelangonline.paging.main.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.lelangonline.models.balance.Balance;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.utils.DataStatus;

import io.reactivex.disposables.CompositeDisposable;

public class BalanceDataSource extends PageKeyedDataSource<Integer, Balance> {

    private static final String TAG = "BalanceDataSource";
    private CompositeDisposable disposable;
    private MainRepository mainRepository;
    private MutableLiveData<DataStatus> mutableLiveData;

    public LiveData<DataStatus> getMutableLiveData() {
        return mutableLiveData;
    }

    public BalanceDataSource(CompositeDisposable disposable, MainRepository mainRepository) {
        this.disposable = disposable;
        this.mainRepository = mainRepository;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Balance> callback) {
        mutableLiveData.postValue(DataStatus.LOADING);
        disposable.add(mainRepository.fetchBalanceFromApi(1, params.requestedLoadSize)
                .subscribe(data -> {
                            if (data.getData().isEmpty())
                                throw new NullPointerException();

                            callback.onResult(data.getData(), null, 2);
                            mutableLiveData.postValue(DataStatus.LOADED);
//                            mainRepository.saveData(data);
//                        }, throwable ->
//                                disposable.add(mainRepository.fetchFromDB(10, 0)
//                                        .subscribe(data -> {
//                                            if (data.isEmpty())
//                                                mutableLiveData.postValue(DataStatus.ERROR);
//                                            else {
//                                                mutableLiveData.postValue(DataStatus.LOADED);
//                                                callback.onResult(data, null, 10);
//                                            }
                                        }, error -> Log.d(TAG, "loadInitial: " + error)
                ));

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Balance> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Balance> callback) {
        disposable.add(
                mainRepository.fetchBalanceFromApi(params.key, params.requestedLoadSize)
                        .subscribe(data -> {
                                    callback.onResult(data.getData(), params.key + 1);
                                    mutableLiveData.postValue(DataStatus.LOADED);
//                                    mainRepository.saveData(data);
//                                }, throwable -> disposable.add(
//                                mainRepository.fetchFromDB(10, params.key)
//                                        .subscribe(data -> {
//                                                    Log.d(TAG, "loadMMM: New Room " + data.size());
//                                                    callback.onResult(data, params.key + 10);
//                                                    mutableLiveData.postValue(DataStatus.LOADED);
                                                },
                                                throwable1 -> Log.d(TAG, "database  ERROR " + throwable1)
                        ));
    }
}
