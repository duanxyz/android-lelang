package com.example.lelangonline.ui.login;

import android.content.SharedPreferences;

import com.example.lelangonline.data.model.ResponseMember;
import com.example.lelangonline.network.main.MainApi;
import com.example.lelangonline.utils.Constants;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
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

    public Call<ResponseMember> requestMember(int id) {
        return mainApi.memberRequest(id);
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
