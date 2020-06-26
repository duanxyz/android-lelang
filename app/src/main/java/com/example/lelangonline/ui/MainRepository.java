package com.example.lelangonline.ui;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lelangonline.database.NewsDao;
import com.example.lelangonline.models.DataItem;
import com.example.lelangonline.models.Response;
import com.example.lelangonline.network.main.MainApi;
import com.example.lelangonline.utils.Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {

    private MainApi mainApi;
    private CompositeDisposable disposable;
    private NewsDao newsDao;
    private SharedPreferences preferences;
    private MutableLiveData<String> avatarImage ;


    @Inject
    public MainRepository(MainApi mainApi, CompositeDisposable disposable,
                          NewsDao newsDao, SharedPreferences preferences) {
        this.mainApi = mainApi;
        this.disposable = disposable;
        this.newsDao = newsDao;
        this.preferences = preferences;
        avatarImage = new MutableLiveData<>();
    }

    public void getAvatarImage() {
        String image = preferences.getString(Constants.AVATAR_PREFS,
                "https://cdn.countryflags.com/thumbs/united-states-of-america/flag-400.png");
        avatarImage.setValue(image);
    }

    public LiveData<String> getSelectedAvatarImage() {
        return avatarImage;
    }

    public void removeDB() {
        disposable.add(
                Single.just(newsDao)
                        .subscribeOn(Schedulers.io())
                        .subscribe(db -> {
                            Log.d("TAG", "removeDB: SUCCESS" );
                            db.deleteBarang();
                        }, throwable -> Log.d("TAG", "removeDB: ERROR" + throwable))
        );
    }

    public Flowable<List<DataItem>> fetchFromDB(int size, int offset) {
        return newsDao.getBarang(size, offset)
                .observeOn(Schedulers.io());
    }

    public void saveData(Response response) {
        newsDao.insertBarang(response.getData());
    }

    public Flowable<Response> fetchFromApi(int page, int size, String category, String search) {
        return mainApi.getItem(page, size, category, search)
                .timeout(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io());
    }


}
