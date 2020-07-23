package com.example.lelangonline.ui.main.home;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.FragmentHomeBinding;

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
    LinearLayoutManager layoutManager;
    @Inject
    HomeAdapter  homeAdapter;
    @Inject
    ConnectivityManager connectivityManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(this, providerFactory).get(HomeViewModel.class);
        homeViewModel.fetchData();
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
        initRecyclersView();
        initRefreshListeners();
        observeObservers();
    }

    private void observeObservers() {
        homeViewModel.getAvatar().observe(getViewLifecycleOwner(), avatar -> {
            fragmentHomeBinding.setAvatarUrl(avatar);
            fragmentHomeBinding.setReqManager(requestManager.setDefaultRequestOptions(requestOptions.error(R.drawable.avatar)));
            fragmentHomeBinding.executePendingBindings();
        });

        homeViewModel.Username().observe(getViewLifecycleOwner(), s -> {
            fragmentHomeBinding.username.setText(s);
        });

        homeViewModel.balance().observe(getViewLifecycleOwner(), s -> {
            fragmentHomeBinding.balance.setText(s);
        });

        homeViewModel.getItemPagedList().observe(getViewLifecycleOwner(), balances -> {
            if (balances != null){
                homeAdapter.submitList(balances);
                fragmentHomeBinding.transactionRV.scrollToPosition(0);
            }
        });
        homeViewModel.getDataStatus().observe(getViewLifecycleOwner(), dataStatus -> {
            if (dataStatus != null) {
                switch (dataStatus) {
                    case ERROR:
                        stopSwipeRefresh();
//                        stopShimmer();
                        fragmentHomeBinding.transactionRV.setVisibility(View.INVISIBLE);
//                        fragmentHomeBinding.noInternetContainer.getRoot().setVisibility(View.VISIBLE);
                        break;
                    case LOADING:
//                        fragmentHomeBinding.shimmerLayout.startShimmer();
                        break;
                    case LOADED:
                        stopSwipeRefresh();
//                        stopShimmer();
                        fragmentHomeBinding.transactionRV.setVisibility(View.VISIBLE);
//                        fragmentHomeBinding.noInternetContainer.getRoot().setVisibility(View.GONE);
                }
            }
        });
    }

    private void initRecyclersView() {
        homeAdapter.setViewModel(homeViewModel);
        fragmentHomeBinding.transactionRV.setLayoutManager(layoutManager);
        fragmentHomeBinding.transactionRV.setAdapter(homeAdapter);
    }

    private void initRefreshListeners() {
        fragmentHomeBinding.swipeRefresh.setOnRefreshListener(() -> homeViewModel.refreshData());
    }

    private void stopSwipeRefresh(){
        fragmentHomeBinding.swipeRefresh.setRefreshing(false);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 101) {
            homeViewModel.refreshData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       fragmentHomeBinding = null;
    }
}
