package com.example.lelangonline.ui.auction.auctionDetail.bid;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.FragmentBidBinding;
import com.example.lelangonline.models.DataItem;
import com.example.lelangonline.ui.auction.AuctionViewModel;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;

public class BidFragment extends DaggerFragment {

    private BidViewModel viewModel;
    private FragmentBidBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;
//    @Inject
//    LinearLayoutManager layoutManager;
//    @Inject
//    BidAdapter bidAdapter;
//    @Inject
//    RequestManager requestManager;
//    @Inject
//    ConnectivityManager connectivityManager;


//    public static BidFragment newInstance() {
//        return new BidFragment();
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, providerFactory).get(BidViewModel.class);
        viewModel.fetchAuctionData();
//        fetchAuctionData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bid, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        initRecyclersView();
//        initRefreshListeners();
//        observeObservers();

    }

//    private void observeObservers() {
//        viewModel.getItemPagedList().observe(getViewLifecycleOwner(), dataItems -> {
//            if(dataItems != null) {
//                bidAdapter.submitList(dataItems);
//                binding.bidRV.scrollToPosition(0);
//            }
//        });
//
//        viewModel.getDataStatus().observe(getViewLifecycleOwner(), dataItems -> {
//            if (dataItems != null) {
//                switch (dataItems) {
//                    case ERROR:
//                        stopSwipeRefresh();
//                        stopShimmer();
//                        binding.bidRV.setVisibility(View.INVISIBLE);
//                        binding.noInternetContainer.getRoot().setVisibility(View.VISIBLE);
//                        break;
//                    case LOADING:
//                        binding.shimmerLayout.startShimmer();
//                        break;
//                    case LOADED:
//                        stopSwipeRefresh();
//                        stopShimmer();
//                        binding.bidRV.setVisibility(View.VISIBLE);
//                        binding.noInternetContainer.getRoot().setVisibility(View.GONE);
////                    case EMPTY:
////                        Toast.makeText(getActivity(), "Field is empty!", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//    }
//
//    private void stopShimmer(){
//        binding.shimmerLayout.stopShimmer();
//        binding.shimmerLayout.setVisibility(View.GONE);
//    }
//
//    private void stopSwipeRefresh(){
//        binding.swipeRefresh.setRefreshing(false);
//    }
//
//    private void initRefreshListeners() {
//        binding.swipeRefresh.setOnRefreshListener(() -> viewModel.refreshData());
//        binding.noInternetContainer.tryAgain.setOnClickListener(v -> viewModel.refreshData());
//    }
//
//    private void initRecyclersView() {
//        bidAdapter.setViewModel(viewModel);
//        binding.bidRV.setLayoutManager(layoutManager);
//        binding.bidRV.setAdapter(bidAdapter);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == Activity.RESULT_OK && requestCode == 101) {
//            viewModel.refreshData();
//        }
//    }
//
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding.bidRV.setLayoutManager(null);
//        binding.bidRV.setAdapter(null);
//        bidAdapter.submitList(null);
//        viewModel.resetItemDetails();
//        binding = null;
//    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(BidViewModel.class);
//        // TODO: Use the ViewModel
//    }

}
