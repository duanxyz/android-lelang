package com.example.lelangonline.ui.withdraw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lelangonline.R;
import com.example.lelangonline.databinding.ActivityWithdrawBinding;

import dagger.android.support.DaggerAppCompatActivity;

public class WithdrawActivity extends DaggerAppCompatActivity {

    private WithdrawViewModel withdrawViewModel;
    private ActivityWithdrawBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
    }
}
