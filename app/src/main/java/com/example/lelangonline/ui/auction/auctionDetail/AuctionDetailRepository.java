package com.example.lelangonline.ui.auction.auctionDetail;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lelangonline.database.NewsDao;
import com.example.lelangonline.database.saved.SavedDao;
import com.example.lelangonline.models.auction.Auction;
import com.example.lelangonline.models.auction.DataItem;
import com.example.lelangonline.models.saved.SavedBarang;
import com.example.lelangonline.network.main.MainApi;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AuctionDetailRepository {
    private SavedDao savedDao;
    private MainApi mainApi;
    private NewsDao newsDao;
    private SharedPreferences preferences;
    private CompositeDisposable disposable;
    private MutableLiveData<Boolean> itemExist;
    private static final String TAG = "DetailsRepository";

    @Inject
    public AuctionDetailRepository(SavedDao savedDao, CompositeDisposable disposable, MainApi mainApi,
                                   NewsDao newsDao, SharedPreferences preferences) {
        this.savedDao = savedDao;
        this.disposable = disposable;
        this.mainApi = mainApi;
        this.preferences = preferences;
        this.newsDao = newsDao;
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


    public Flowable<Auction> fetchFromApi(int page, int size, String item_id) {
        return mainApi.getAuction(item_id, page, size)
                .timeout(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io());
    }

    public void saveData(Auction data) {
        newsDao.insertAuction(data.getData());
    }

    public Flowable<List<DataItem>> fetchFromDB(int size, int offset) {
        return newsDao.getAuction(size, offset)
                .observeOn(Schedulers.io());
    }

    public void removeDB() {
        disposable.add(
                Single.just(newsDao)
                        .subscribeOn(Schedulers.io())
                        .subscribe(db -> {
                            Log.d("TAG", "removeDB: SUCCESS" );
                            db.deleteAuction();
                        }, throwable -> Log.d("TAG", "removeDB: ERROR" + throwable))
        );
    }
}
