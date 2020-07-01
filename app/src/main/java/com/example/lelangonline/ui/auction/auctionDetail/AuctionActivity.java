package com.example.lelangonline.ui.auction.auctionDetail;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.RequestManager;
import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.ActivityAuctionBinding;
import com.example.lelangonline.models.saved.SavedBarang;

import com.example.lelangonline.ui.auction.auctionDetail.bid.BidAdapter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuctionActivity extends DaggerAppCompatActivity {

    private AuctionDetailViewModel viewModel;
    private ActivityAuctionBinding binding;

    @Inject
    RequestManager requestManager;

    @Inject
    ViewModelProviderFactory providerFactory;
    @Inject
    LinearLayoutManager layoutManager;
    @Inject
    BidAdapter bidAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auction);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this, providerFactory).get(AuctionDetailViewModel.class);
        SavedBarang dataItem = getIntent().getExtras().getParcelable("item");

//        initBinding();
//        Source source = getIntent().getExtras().getParcelable("source");

        binding.setViewModel(viewModel);
        binding.setReqManager(requestManager);
        viewModel.fetchAuctionData();
        binding.setItem(dataItem);
        viewModel.getAuction(String.valueOf(dataItem.getId()));
        initRecyclersView();
        viewModel.checkIfItemExist(dataItem.getId());
        observeObservers(dataItem.getId());
//        observe();
//        initView();

//        initRefreshListeners();

//        binding.setSource(source);
    }

//    private void initBinding() {
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_auction);
//        binding.setLifecycleOwner(this);
//        viewModel = new ViewModelProvider(this, providerFactory).get(AuctionDetailViewModel.class);
//    }

//    private void initView() {
//        SavedBarang dataItem = getIntent().getExtras().getParcelable("item");
////        Source source = getIntent().getExtras().getParcelable("source");
//
//        binding.setViewModel(viewModel);
//        binding.setReqManager(requestManager);
//        binding.setItem(dataItem);
////        viewModel.checkIfItemExist(dataItem.getId());
//        observeObservers(dataItem.getId());
//    }

    private void observeObservers(int id) {
        viewModel.getCloseItem().observe(this, close -> {
            if (close)
                finish();
        });

        viewModel.getItemPagedList().observe(this, dataItems -> {
            if(dataItems != null) {
                bidAdapter.submitList(dataItems);
                binding.auctionDetailRV.scrollToPosition(0);
            }
        });

        viewModel.getDataStatus().observe(this, dataItems -> {
            if (dataItems != null) {
                switch (dataItems) {
                    case ERROR:
//                        stopSwipeRefresh();
//                        stopShimmer();
                        binding.auctionDetailRV.setVisibility(View.INVISIBLE);
//                        binding.noInternetContainer.getRoot().setVisibility(View.VISIBLE);
                        break;
                    case LOADING:
//                        binding.shimmerLayout.startShimmer();
                        break;
                    case LOADED:
//                        stopSwipeRefresh();
//                        stopShimmer();
                        binding.auctionDetailRV.setVisibility(View.VISIBLE);
//                        viewModel.getAuction(String.valueOf(id));

//                        binding.noInternetContainer.getRoot().setVisibility(View.GONE);
//                    case EMPTY:
//                        Toast.makeText(getActivity(), "Field is empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

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

    private void initRecyclersView() {
        bidAdapter.setViewModel(viewModel);
        binding.auctionDetailRV.setLayoutManager(layoutManager);
        binding.auctionDetailRV.setAdapter(bidAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 101) {
            viewModel.refreshData();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.auctionDetailRV.setLayoutManager(null);
        binding.auctionDetailRV.setAdapter(null);
        bidAdapter.submitList(null);
        viewModel.resetItemDetails();
        binding = null;
    }


}
