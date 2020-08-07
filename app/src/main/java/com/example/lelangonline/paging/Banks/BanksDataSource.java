package com.example.lelangonline.paging.Banks;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.lelangonline.models.banks.Banks;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.utils.DataStatus;

import io.reactivex.disposables.CompositeDisposable;

public class BanksDataSource extends PageKeyedDataSource<Integer, Banks> {
    private static final String TAG = "NewsDataSource";
    private CompositeDisposable disposable;
    private MainRepository repository;
    private MutableLiveData<DataStatus> mutableLiveData;

    public LiveData<DataStatus> getMutableLiveData() {
        return mutableLiveData;
    }

    public BanksDataSource(CompositeDisposable disposable, MainRepository repository) {
        this.disposable = disposable;
        this.repository = repository;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Banks> callback) {
        mutableLiveData.postValue(DataStatus.LOADING);
        disposable.add(repository.fetchBankFromApi(1, params.requestedLoadSize)
                .subscribe(data -> {
                            if (data.getData().isEmpty())
                                throw new NullPointerException();

                            callback.onResult(data.getData(), null, 2);
                            mutableLiveData.postValue(DataStatus.LOADED);
//                            repository.saveBanks(data);
                                        }, error -> Log.d(TAG, "loadInitial: " + error)
                ));

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Banks> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Banks> callback) {
//        disposable.add(
//                repository.fetchBankFromApi(params.key, params.requestedLoadSize)
//                        .subscribe(data -> {
//                                    callback.onResult(data.getData(), params.key + 1);
//                                    mutableLiveData.postValue(DataStatus.LOADED);
//                                    repository.saveBanks(data);
//                                }, throwable1 -> Log.d(TAG, "database  ERROR " + throwable1)
//
//                        ));
    }
}
