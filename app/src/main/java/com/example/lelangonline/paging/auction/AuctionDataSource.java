package com.example.lelangonline.paging.auction;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.lelangonline.models.auction.DataItem;
import com.example.lelangonline.ui.auctionDetail.AuctionDetailRepository;
import com.example.lelangonline.utils.DataStatus;

import io.reactivex.disposables.CompositeDisposable;

public class AuctionDataSource extends PageKeyedDataSource<Integer, DataItem> {
    private static final String TAG = "NewsDataSource";
    private CompositeDisposable disposable;
    private AuctionDetailRepository repository;
    private String item_id;
    private MutableLiveData<DataStatus> mutableLiveData;

    public LiveData<DataStatus> getMutableLiveData() {
        return mutableLiveData;
    }

    public AuctionDataSource(CompositeDisposable disposable, AuctionDetailRepository repository, String item_id) {
        this.disposable = disposable;
        this.repository = repository;
        mutableLiveData = new MutableLiveData<>();
        this.item_id = item_id;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, DataItem> callback) {
        mutableLiveData.postValue(DataStatus.LOADING);
        disposable.add(repository.fetchFromApi(1, params.requestedLoadSize, item_id)
                .subscribe(data -> {
                            if (data.getData().isEmpty())
                                throw new NullPointerException();

                            callback.onResult(data.getData(), null, 2);
                            mutableLiveData.postValue(DataStatus.LOADED);
                            repository.saveData(data);
                        }, throwable ->
                                disposable.add(repository.fetchFromDB(10, 0)
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
                repository.fetchFromApi(params.key, params.requestedLoadSize, item_id)
                        .subscribe(data -> {
                                    callback.onResult(data.getData(), params.key + 1);
                                    mutableLiveData.postValue(DataStatus.LOADED);
                                    repository.saveData(data);
                                }, throwable -> disposable.add(
                                repository.fetchFromDB(10, params.key)
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
