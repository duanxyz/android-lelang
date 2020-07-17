package com.example.lelangonline.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.lelangonline.R;
import com.example.lelangonline.databinding.ActivityMainBinding;
import com.example.lelangonline.ui.login.LoginActivity;
import com.example.lelangonline.utils.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
//        binding.setDate(Constants.getTodayDate());

        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(binding.mainBottomNav, navController);

    }


    private void killProcess(){
        android.os.Process.killProcess(android.os.Process.myPid());
    }

//    public void logout(){
//        Intent intent = new Intent(, LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//        finish();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killProcess();
    }

}
