package com.example.lelangonline.ui.barang;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.FragmentBarangBinding;
import com.example.lelangonline.models.DataItem;
import com.example.lelangonline.ui.auction.auctionDetail.bid.BidFragment;
import com.example.lelangonline.ui.details.DetailsActivity;
import com.example.lelangonline.utils.Constants;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;

public class BarangFragment extends DaggerFragment {

    private BarangViewModel viewModel;
    private FragmentBarangBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;
    @Inject
    @Named("vertical")
    LinearLayoutManager layoutManager;
    @Inject
    @Named("horizontal")
    LinearLayoutManager horizontalLayoutManager;
    @Inject
    BarangAdapter barangAdapter;
    @Inject
    CategoryItemAdapter categoryItemAdapter;
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
        viewModel = new ViewModelProvider(this, providerFactory).get(BarangViewModel.class);
        viewModel.  fetchTopNewsData();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_barang, container, false);
        binding.setReqManager(requestManager.setDefaultRequestOptions(requestOptions));
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclersView();
        initRefreshListeners();
        observeObservers();
//        changeFlag();
//        binding.flagImage.setOnClickListener(this::changeLanguage);
    }

    private void changeFlag() {
        viewModel.getCountry().observe(getViewLifecycleOwner(), countryImage -> {
            binding.setFlagUrl(countryImage);
            binding.setReqManager(requestManager.setDefaultRequestOptions(requestOptions.error(R.drawable.avatar)));
            binding.executePendingBindings();
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initRecyclersView() {
        barangAdapter.setViewModel(viewModel);
        binding.homeRV.setLayoutManager(layoutManager);
        binding.homeRV.setAdapter(barangAdapter);

        //category
        binding.categoryItemsRV.setLayoutManager(horizontalLayoutManager);
        binding.categoryItemsRV.setAdapter(categoryItemAdapter);
        categoryItemAdapter.setViewModel(viewModel);

        binding.homeRV.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(scrollY > oldScrollY)
                binding.categoryItemsRV.animate().translationY(-1000).setInterpolator(new AccelerateInterpolator()).start();
            else if(scrollY < oldScrollY || scrollY == 0)
                binding.categoryItemsRV.animate().translationY(0).setInterpolator(new AccelerateInterpolator()).start();
        });
    }

    private void stopSwipeRefresh(){
        binding.swipeRefresh.setRefreshing(false);
    }

    private void initRefreshListeners(){
        binding.swipeRefresh.setOnRefreshListener(() -> viewModel.refreshData());
        binding.noInternetContainer.tryAgain.setOnClickListener(v -> viewModel.refreshData());
    }

    private void stopShimmer(){
        binding.shimmerLayout.stopShimmer();
        binding.shimmerLayout.setVisibility(View.GONE);
    }

    private void observeObservers() {

        viewModel.getItemPagedList().observe(getViewLifecycleOwner(), articlesItems -> {
            if(articlesItems != null) {
                barangAdapter.submitList(articlesItems);
                binding.homeRV.scrollToPosition(0);
            }
        });

        viewModel.getSelectedItem().observe(getViewLifecycleOwner(), position -> {
            binding.categoryItemsRV.scrollToPosition(position);
            categoryItemAdapter.setPositionNum(position);
        });

        viewModel.getDataStatus().observe(getViewLifecycleOwner(), articlesItems -> {
            if (articlesItems != null) {
                switch (articlesItems) {
                    case ERROR:
                        stopSwipeRefresh();
                        stopShimmer();
                        binding.homeRV.setVisibility(View.INVISIBLE);
                        binding.noInternetContainer.getRoot().setVisibility(View.VISIBLE);
                        break;
                    case LOADING:
                        binding.shimmerLayout.startShimmer();
                        break;
                    case LOADED:
                        stopSwipeRefresh();
                        stopShimmer();
                        binding.homeRV.setVisibility(View.VISIBLE);
                        binding.noInternetContainer.getRoot().setVisibility(View.GONE);
//                    case EMPTY:
//                        Toast.makeText(getActivity(), "Field is empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        viewModel.observeArticleDetails().observe(getViewLifecycleOwner(), articlesItem -> {
            if(articlesItem != null)
                openArticleDetails(articlesItem);
        });
    }


    private void openArticleDetails(DataItem articlesItem) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("item", Constants.convertBarangClass(articlesItem));
//        intent.putExtra("source", articlesItem.getSource());
        startActivity(intent);
    }

    private void changeLanguage(View view) {
//        if(Constants.isConnected(connectivityManager)){
//            Intent intent = new Intent(getActivity(), LanguageActivity.class);
//            startActivityForResult(intent, 101);
//        } else{
//            Snackbar.make(view, "You are offline", Snackbar.LENGTH_SHORT)
//                    .setBackgroundTint(Color.RED).show();
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 101) {
            viewModel.refreshData();
            changeFlag();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.homeRV.setLayoutManager(null);
        binding.homeRV.setAdapter(null);
        barangAdapter.submitList(null);
        viewModel.resetArticleDetails();
        binding = null;
    }


//    @Inject
//    @Named("vertical")
//    LinearLayoutManager verticalLayoutManager;
//    @Inject
//    @Named("horizontal")
//    LinearLayoutManager horizontalLayoutManager;
//    @Inject
//    BarangItemAdapter barangItemAdapter;
//    @Inject
//    BarangMainAdapter mainAdapter;
//    @Inject
//    ViewModelProviderFactory providerFactory;
//
//    private BarangViewModel mViewModel;
//    private FragmentBarangBinding binding;
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_barang, container, false);
//        mViewModel = new ViewModelProvider(this, providerFactory).get(BarangViewModel.class);
//
//        initRecyclerView();
//        initRefreshListeners();
//        observeObservers();
//        return binding.getRoot();
//
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        mViewModel.fetchTopNewsData();
//    }
//
//    private void observeObservers() {
//        mViewModel.getBarangDetails().observe(getViewLifecycleOwner(), dataItem -> {
//            if (dataItem != null)
//                openBarangDetails(dataItem);
//        });
//
//        mViewModel.getSelectedItem().observe(getViewLifecycleOwner(), position -> {
//            binding.categoryItemsRV.scrollToPosition(position);
//            barangItemAdapter.setPositionNum(position);
//        });
//
//        mViewModel.getItemPagedList().observe(getViewLifecycleOwner(), barang -> {
//            mainAdapter.submitList(null);
//            mainAdapter.submitList(barang);
//            binding.categoryRV.scrollToPosition(0);
//        });
//
//
//        mViewModel.getDataStatus().observe(getViewLifecycleOwner(), dataStatus -> {
//            switch (dataStatus) {
//                case LOADED:
//                    stopSwipeRefresh();
//                    binding.categoryRV.setVisibility(View.VISIBLE);
//                    binding.noInternetContainer.getRoot().setVisibility(View.GONE);
//                    break;
//                case LOADING:
//                    binding.swipeRefresh.setRefreshing(true);
//                    binding.noInternetContainer.getRoot().setVisibility(View.GONE);
//                    break;
//                case ERROR:
//                    stopSwipeRefresh();
//                    binding.categoryRV.setVisibility(View.GONE);
//                    binding.noInternetContainer.getRoot().setVisibility(View.VISIBLE);
//            }
//
//        });
//    }
//
//    private void stopSwipeRefresh() {
//        binding.swipeRefresh.setRefreshing(false);
//    }
//
//    private void openBarangDetails(DataItem dataItem) {
////        Intent intent = new Intent(getActivity(), DetailsActivity.class);
////        intent.putExtra("article", Constants.convertArticleClass(articlesItem));
////        intent.putExtra("source", articlesItem.getSource());
////        startActivity(intent);
//    }
//
//    private void initRefreshListeners() {
//        binding.swipeRefresh.setOnRefreshListener(() -> mViewModel.refreshData());
//        binding.noInternetContainer.tryAgain.setOnClickListener(v -> mViewModel.refreshData());
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void initRecyclerView() {
//        binding.categoryRV.setLayoutManager(verticalLayoutManager);
//        binding.categoryRV.setAdapter(mainAdapter);
//        binding.categoryItemsRV.setLayoutManager(horizontalLayoutManager);
//        binding.categoryItemsRV.setAdapter(barangItemAdapter);
//        barangItemAdapter.setViewModel(mViewModel);
//        mainAdapter.setViewModel(mViewModel);
//
//        binding.categoryRV.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
//            if(scrollY > oldScrollY)
//                binding.categoryItemsRV.animate().translationY(-1000).setInterpolator(new AccelerateInterpolator()).start();
//            else if(scrollY < oldScrollY || scrollY == 0)
//                binding.categoryItemsRV.animate().translationY(0).setInterpolator(new AccelerateInterpolator()).start();
//        });
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        mViewModel.resetItemDetails();
//    }

}
