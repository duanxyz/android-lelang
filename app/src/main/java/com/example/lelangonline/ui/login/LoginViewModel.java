package com.example.lelangonline.ui.login;

import android.content.SharedPreferences;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lelangonline.data.model.LoggedInUser;
import com.example.lelangonline.utils.Constants;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {


    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    private MutableLiveData<LoggedInUser> userMutableLiveData;
    private LoginRepository loginRepository;
    private SharedPreferences.Editor editor;



    @Inject
    LoginViewModel(LoginRepository loginRepository, SharedPreferences.Editor editor) {
        this.loginRepository = loginRepository;
        this.editor = editor;
    }

    public MutableLiveData<LoggedInUser> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }

    public void onClick(View view) {
//        editor.putString(Constants.COUNTRY_PREFS_NAME, "country.getSmallName()").apply();


//        loginRepository.requestLogin(EmailAddress.getValue(), Password.getValue());

        LoggedInUser loggedInUser = new LoggedInUser(EmailAddress.getValue(), Password.getValue());


        userMutableLiveData.setValue(loggedInUser);

    }

    public void setPrefUser(String id, String email, String username, String memberid) {
        editor.putString(Constants.USERID_PREFS, id);
        editor.putString(Constants.EMAIL_PREFS, email);
        editor.putString(Constants.USERNAME_PREFS, username);
        editor.putString(Constants.MEMBERID_PREFS, memberid);
        editor.putBoolean(Constants.SESSION_PREFS, true);
        editor.apply();
    }
}
