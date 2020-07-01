package com.example.lelangonline.ui.barang;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
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

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.FragmentBarangBinding;
import com.example.lelangonline.models.DataItem;
import com.example.lelangonline.ui.details.DetailsActivity;
import com.example.lelangonline.utils.Constants;

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
        viewModel.fetchBarangData();
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
        //animation category
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
        viewModel.getItemPagedList().observe(getViewLifecycleOwner(), barangItems -> {
            if(barangItems != null) {
                barangAdapter.submitList(barangItems);
                binding.homeRV.scrollToPosition(0);
            }
        });

        viewModel.getSelectedItem().observe(getViewLifecycleOwner(), position -> {
            binding.categoryItemsRV.scrollToPosition(position);
            categoryItemAdapter.setPositionNum(position);
        });

        viewModel.getDataStatus().observe(getViewLifecycleOwner(), barangItems -> {
            if (barangItems != null) {
                switch (barangItems) {
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
                }
            }
        });

        viewModel.observeArticleDetails().observe(getViewLifecycleOwner(), barangItems -> {
            if(barangItems != null)
                openArticleDetails(barangItems);
        });
    }


    private void openArticleDetails(DataItem barangItems) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("item", Constants.convertBarangClass(barangItems));
        startActivity(intent);
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
        binding.homeRV.setLayoutManager(null);
        binding.homeRV.setAdapter(null);
        barangAdapter.submitList(null);
        viewModel.resetArticleDetails();
        binding = null;
    }
}
