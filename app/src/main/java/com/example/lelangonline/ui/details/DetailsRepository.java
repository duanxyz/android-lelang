package com.example.lelangonline.ui.details;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lelangonline.database.saved.SavedDao;
import com.example.lelangonline.models.saved.SavedBarang;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DetailsRepository {
    private SavedDao savedDao;
    private CompositeDisposable disposable;
    private MutableLiveData<Boolean> itemExist;
    private static final String TAG = "DetailsRepository";

    @Inject
    public DetailsRepository(SavedDao savedDao, CompositeDisposable disposable) {
        this.savedDao = savedDao;
        this.disposable = disposable;
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
}
