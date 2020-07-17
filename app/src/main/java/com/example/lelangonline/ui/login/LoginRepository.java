package com.example.lelangonline.ui.login;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.lelangonline.data.model.ResponseLogin;
import com.example.lelangonline.data.model.ResponseMember;
import com.example.lelangonline.network.main.MainApi;
import com.example.lelangonline.utils.Constants;
import com.example.lelangonline.utils.DataStatus;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class LoginRepository {

    private MainApi mainApi;
    private CompositeDisposable disposable;
    private SharedPreferences preferences;
    private SharedPreferences.Editor mEditor;
   

    @Inject
    public LoginRepository(MainApi mainApi, CompositeDisposable disposable, SharedPreferences.Editor editor) {
        this.mainApi = mainApi;
        this.disposable = disposable;
        this.mEditor = editor;
    }

    public Call<ResponseBody> requestLogin(String email, String password) {
        return mainApi.loginRequest(email, password);
    }

//    @SuppressLint("CheckResult")
//    public void login(String email, String password) {
//         mainApi.login(email, password)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(responseLogin -> {
//                    if (responseLogin.)
//                } );
//    }

    public void requestMember(int id) {
//        return mainApi.memberRequest(id);
        disposable.add(
                mainApi.fetchMembersById(id)
                        .timeout(3, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .subscribe(memebers -> {
                                    if (memebers.getData().isEmpty())
                                        throw new NullPointerException();
                            mEditor.putString(Constants.NAME_PREFS, memebers.getData().get(0).getName());
                            mEditor.putString(Constants.PHONE_PREFS, memebers.getData().get(0).getPhoneNumber());
                            mEditor.putString(Constants.STATUS_PREFS, memebers.getData().get(0).getStatus());
                            mEditor.putInt(Constants.SALDO_PREFS, memebers.getData().get(0).getSaldo());
                            mEditor.putString(Constants.AVATAR_PREFS, memebers.getData().get(0).getAvatar());
                            mEditor.apply();
                                }, error -> Log.d("TAG", "loadInitial: " + error)
                        )
        );
//                .enqueue(new Callback<ResponseMember>() {
//            @Override
//            public void onResponse(Call<ResponseMember> call, Response<ResponseMember> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseMember> call, Throwable t) {
//
//            }
//        });
    }

    public void setPrefMember(String name, String phone, String status, int saldo, String avatar) {
        mEditor.putString(Constants.NAME_PREFS, name);
        mEditor.putString(Constants.PHONE_PREFS, phone);
        mEditor.putString(Constants.STATUS_PREFS, status);
        mEditor.putInt(Constants.SALDO_PREFS, saldo);
        mEditor.putString(Constants.AVATAR_PREFS, avatar);
        mEditor.apply();

    }
}
