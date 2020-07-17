package com.example.lelangonline.ui.main.home;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.FragmentHomeBinding;
import com.ramotion.foldingcell.FoldingCell;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding fragmentHomeBinding;

    @Inject
    ViewModelProviderFactory providerFactory;
    @Named("circleRequestOption")
    @Inject
    RequestOptions requestOptions;
    @Inject
    RequestManager requestManager;
    @Inject
    ConnectivityManager connectivityManager;
    private FoldingCell foldingCell;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(this, providerFactory).get(HomeViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        fragmentHomeBinding.setReqManager(requestManager.setDefaultRequestOptions(requestOptions));
//        fragmentHomeBinding.content.setReqManager1(requestManager.setDefaultRequestOptions(requestOptions));
        fragmentHomeBinding.setLifecycleOwner(this);
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        foldingCell();
        initRefreshListeners();
        avatarImageTitle();
        setUsername();
        setSaldo();
//        avatarImageContent();
    }

    private void setSaldo() {
        homeViewModel.balance().observe(getViewLifecycleOwner(), s -> {
            fragmentHomeBinding.textView9.setText(s);
        });
    }

    private void setUsername() {
        homeViewModel.Username().observe(getViewLifecycleOwner(), s -> {
            fragmentHomeBinding.textView8.setText(s);
        });
    }

    private void initRefreshListeners() {
        fragmentHomeBinding.swipeRefresh.setOnRefreshListener(() -> homeViewModel.refreshData());
    }

    private void stopSwipeRefresh(){
        fragmentHomeBinding.swipeRefresh.setRefreshing(false);
    }

//    private void avatarImageContent() {
//        homeViewModel.getAvatar().observe(getViewLifecycleOwner(), avatar -> {
//            fragmentHomeBinding.content.setAvatarUrl1(avatar);
//            fragmentHomeBinding.content.setReqManager1(requestManager.setDefaultRequestOptions(requestOptions.error(R.drawable.avatar)));
//            fragmentHomeBinding.content.executePendingBindings();
//        });
//    }

    private void avatarImageTitle() {
        homeViewModel.getAvatar().observe(getViewLifecycleOwner(), avatar -> {
            fragmentHomeBinding.setAvatarUrl(avatar);
            fragmentHomeBinding.setReqManager(requestManager.setDefaultRequestOptions(requestOptions.error(R.drawable.avatar)));
            fragmentHomeBinding.executePendingBindings();
        });
    }

//    private void foldingCell() {
//        foldingCell = fragmentHomeBinding.foldingCell;
//        foldingCell.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                foldingCell.toggle(false);
//            }
//        });
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 101) {
            homeViewModel.refreshData();
//            avatarImageContent();
            avatarImageTitle();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       fragmentHomeBinding = null;
       foldingCell = null;
    }
}
