package com.example.lelangonline.ui.profile;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.FragmentProfileBinding;
import com.example.lelangonline.models.users.DataItem;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {
    private ProfileViewModel mViewModel;
    private FragmentProfileBinding binding;
    @Inject
    ViewModelProviderFactory providerFactory;
    @Named("circleRequestOption")
    @Inject
    RequestOptions requestOptions;
    @Inject
    RequestManager requestManager;
    @Inject
    ConnectivityManager connectivityManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this, providerFactory).get(ProfileViewModel.class);
//        mViewModel.getMembers();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false);
        binding.setReqManager(requestManager.setDefaultRequestOptions(requestOptions));
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAvatar();
//        initRefreshListeners();
        observable();
    }

    private void observable() {
        mViewModel.fetchMember().observe(getViewLifecycleOwner(),dataItem -> {
            if (dataItem != null)
                binding.setData(dataItem);
            else Log.d("else ", "blom berisi");
        });
        mViewModel.getMembers().observe(getViewLifecycleOwner(), data -> {
            if (data != null) {
                switch (data) {
                    case ERROR:
                        Log.d("TAG", "dataStatus :"+data);
//                        stopSwipeRefresh();
//                        stopShimmer();
//                        binding.homeRV.setVisibility(View.INVISIBLE);
//                        binding.noInternetContainer.getRoot().setVisibility(View.VISIBLE);
                        break;
                    case LOADING:
                        Log.d("TAG", "dataStatus :"+data);
                        binding.textView2.setVisibility(View.GONE);
                        binding.saldo.setVisibility(View.GONE);
//                        binding.shimmerLayout.startShimmer();
                        break;
                    case LOADED:
                        Log.d("TAG", "dataStatus :"+data);
//                        stopSwipeRefresh();
//                        stopShimmer();
                        binding.textView2.setVisibility(View.VISIBLE);
                        binding.saldo.setVisibility(View.VISIBLE);
//                        binding.noInternetContainer.getRoot().setVisibility(View.GONE);
                }
            }
        });
    }

    private void setAvatar() {
        mViewModel.getAvatar().observe(getViewLifecycleOwner(), avatar -> {
            binding.setAvatarUrl(avatar);
            binding.setReqManager(requestManager.setDefaultRequestOptions(requestOptions.error(R.drawable.avatar)));
            binding.executePendingBindings();
        });
    }

    private void initRefreshListeners() {
        binding.swipeRefresh.setOnRefreshListener(() -> mViewModel.refreshData());
    }

    private void stopSwipeRefresh(){
        binding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 101) {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
