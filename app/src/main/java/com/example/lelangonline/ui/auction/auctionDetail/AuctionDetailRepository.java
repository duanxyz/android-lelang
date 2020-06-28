package com.example.lelangonline.ui.auction.auctionDetail;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lelangonline.database.saved.SavedDao;
import com.example.lelangonline.models.auction.DataItem;
import com.example.lelangonline.models.saved.SavedBarang;
import com.example.lelangonline.network.main.MainApi;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AuctionDetailRepository {
    private SavedDao savedDao;
    private MainApi mainApi;
    private CompositeDisposable disposable;
    private MutableLiveData<Boolean> itemExist;
    private static final String TAG = "DetailsRepository";

    @Inject
    public AuctionDetailRepository(SavedDao savedDao, CompositeDisposable disposable, MainApi mainApi) {
        this.savedDao = savedDao;
        this.disposable = disposable;
        this.mainApi = mainApi;
        itemExist = new MutableLiveData<>();
    }


    void itemStatus(SavedBarang savedBarang){
        disposable.add(
                getDatabase()
                        .subscribe(db->{

                            if(db.getSpecificSavedBarang(savedBarang.getId()) == 1)
                                db.deleteSavedBarang(savedBarang.getId());
                            else
                                db.saveBarang(savedBarang);

                            checkItemExist(savedBarang.getId());
                        }, throwable -> Log.d(TAG, "itemStatus: " + throwable))
        );
    }


    void checkItemExist(int id) {
        disposable.add(itemExistSingle(id)
                .subscribe(success -> {
                            if (success > 0)
                                itemExist.postValue(true);
                            else
                                itemExist.postValue(false);
                        },
                        error -> itemExist.postValue(false))
        );
    }

    private Single<SavedDao> getDatabase() {
        return Single.just(savedDao)
                .subscribeOn(Schedulers.io());
    }

    private Single<Integer> itemExistSingle(int id) {
        return savedDao
                .singleSpecificSavedBarang(id)
                .subscribeOn(Schedulers.io());
    }

    LiveData<Boolean> getItemExist() {
        return itemExist;
    }

    @SuppressLint("CheckResult")
    public void postAuction(DataItem dataItem) {
        mainApi.createAuction(dataItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
