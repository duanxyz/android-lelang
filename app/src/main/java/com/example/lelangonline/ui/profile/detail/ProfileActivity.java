package com.example.lelangonline.ui.profile.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.bumptech.glide.RequestManager;
import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.ActivityProfileBinding;
import com.example.lelangonline.models.users.DataItem;
import com.example.lelangonline.models.users.User;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import dagger.android.support.DaggerFragment;

public class ProfileActivity extends DaggerAppCompatActivity {

    private ProfileDetailViewModel viewModel;
    private ActivityProfileBinding binding;

    @Inject
    RequestManager requestManager;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this, providerFactory).get(ProfileDetailViewModel.class);
        DataItem dataItem = getIntent().getExtras().getParcelable("data");

        binding.setViewModel(viewModel);
//        binding.setReqManager(requestManager);
        binding.setData(dataItem);
        observeObservers();

    }

    private void observeObservers() {
        viewModel.getCloseItem().observe(this, close -> {
            if (close)
                finish();
        });
    }
}
