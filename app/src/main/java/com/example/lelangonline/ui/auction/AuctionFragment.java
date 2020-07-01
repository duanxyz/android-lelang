package com.example.lelangonline.ui.auction;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.FragmentAuctionBinding;
import com.example.lelangonline.models.DataItem;
import com.example.lelangonline.ui.auction.auctionDetail.AuctionActivity;
import com.example.lelangonline.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;

public class AuctionFragment extends DaggerFragment {

    private AuctionViewModel viewModel;
    private FragmentAuctionBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;
    @Inject
    LinearLayoutManager layoutManager;
    @Inject
    AuctionAdapter auctionAdapter;
    @Named("circleRequestOption")
    @Inject
    RequestOptions requestOptions;
    @Inject
    RequestManager requestManager;
    @Inject
    ConnectivityManager connectivityManager;


//    public static AuctionFragment newInstance() {
//        return new AuctionFragment();
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, providerFactory).get(AuctionViewModel.class);
        viewModel.fetchAuctionData();
        fetchAuctionData();
    }

    private void fetchAuctionData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());
        viewModel.fetchAuctionItem(currentDateandTime);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_auction, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclersView();
        initRefreshListeners();
        observeObservers();

//        changeFlag();
//        binding.flagImage.setOnClickListener(this::changeLanguage);
    }

    private void observeObservers() {
        viewModel.getItemPagedList().observe(getViewLifecycleOwner(), dataItems -> {
            if(dataItems != null) {
                auctionAdapter.submitList(dataItems);
                binding.auctionRV.scrollToPosition(0);
            }
        });

        viewModel.getDataStatus().observe(getViewLifecycleOwner(), dataItems -> {
            if (dataItems != null) {
                switch (dataItems) {
                    case ERROR:
                        stopSwipeRefresh();
                        stopShimmer();
                        binding.auctionRV.setVisibility(View.INVISIBLE);
                        binding.noInternetContainer.getRoot().setVisibility(View.VISIBLE);
                        break;
                    case LOADING:
                        binding.shimmerLayout.startShimmer();
                        break;
                    case LOADED:
                        stopSwipeRefresh();
                        stopShimmer();
                        binding.auctionRV.setVisibility(View.VISIBLE);
                        binding.noInternetContainer.getRoot().setVisibility(View.GONE);
//                    case EMPTY:
//                        Toast.makeText(getActivity(), "Field is empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        viewModel.observeAuctionDetails().observe(getViewLifecycleOwner(), dataItems -> {
            if(dataItems != null)
                openAuctionDetails(dataItems);
        });

    }

    private void openAuctionDetails(DataItem dataItems) {
        Intent intent = new Intent(getActivity(), AuctionActivity.class);
        intent.putExtra("item", Constants.convertBarangClass(dataItems));
//        intent.putExtra("source", articlesItem.getSource());
        startActivity(intent);
    }

    private void stopShimmer(){
        binding.shimmerLayout.stopShimmer();
        binding.shimmerLayout.setVisibility(View.GONE);
    }

    private void stopSwipeRefresh(){
        binding.swipeRefresh.setRefreshing(false);
    }

    private void initRefreshListeners() {
        binding.swipeRefresh.setOnRefreshListener(() -> viewModel.refreshData());
        binding.noInternetContainer.tryAgain.setOnClickListener(v -> viewModel.refreshData());
    }

    private void initRecyclersView() {
        auctionAdapter.setViewModel(viewModel);
        binding.auctionRV.setLayoutManager(layoutManager);
        binding.auctionRV.setAdapter(auctionAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 101) {
            viewModel.refreshData();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.auctionRV.setLayoutManager(null);
        binding.auctionRV.setAdapter(null);
        auctionAdapter.submitList(null);
        viewModel.resetItemDetails();
        binding = null;
    }
}
