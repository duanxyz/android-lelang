package com.example.lelangonline.ui.barang;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.FragmentBarangBinding;
import com.example.lelangonline.models.DataItem;
import com.example.lelangonline.utils.Constants;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;

public class BarangFragment extends DaggerFragment {

    @Inject
    @Named("vertical")
    LinearLayoutManager verticalLayoutManager;
    @Inject
    @Named("horizontal")
    LinearLayoutManager horizontalLayoutManager;
    @Inject
    BarangItemAdapter barangItemAdapter;
    @Inject
    BarangMainAdapter mainAdapter;
    @Inject
    ViewModelProviderFactory providerFactory;

    private BarangViewModel mViewModel;
    private FragmentBarangBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_barang, container, false);
        mViewModel = new ViewModelProvider(this, providerFactory).get(BarangViewModel.class);

        initRecyclerView();
        initRefreshListeners();
        observeObservers();
        return binding.getRoot();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mViewModel.fetchTopNewsData();
    }

    private void observeObservers() {
        mViewModel.getBarangDetails().observe(getViewLifecycleOwner(), dataItem -> {
            if (dataItem != null)
                openBarangDetails(dataItem);
        });

        mViewModel.getSelectedItem().observe(getViewLifecycleOwner(), position -> {
            binding.categoryItemsRV.scrollToPosition(position);
            barangItemAdapter.setPositionNum(position);
        });

        mViewModel.getItemPagedList().observe(getViewLifecycleOwner(), barang -> {
            mainAdapter.submitList(null);
            mainAdapter.submitList(barang);
            binding.categoryRV.scrollToPosition(0);
        });


        mViewModel.getDataStatus().observe(getViewLifecycleOwner(), dataStatus -> {
            switch (dataStatus) {
                case LOADED:
                    stopSwipeRefresh();
                    binding.categoryRV.setVisibility(View.VISIBLE);
                    binding.noInternetContainer.getRoot().setVisibility(View.GONE);
                    break;
                case LOADING:
                    binding.swipeRefresh.setRefreshing(true);
                    binding.noInternetContainer.getRoot().setVisibility(View.GONE);
                    break;
                case ERROR:
                    stopSwipeRefresh();
                    binding.categoryRV.setVisibility(View.GONE);
                    binding.noInternetContainer.getRoot().setVisibility(View.VISIBLE);
            }

        });
    }

    private void stopSwipeRefresh() {
        binding.swipeRefresh.setRefreshing(false);
    }

    private void openBarangDetails(DataItem dataItem) {
//        Intent intent = new Intent(getActivity(), DetailsActivity.class);
//        intent.putExtra("article", Constants.convertArticleClass(articlesItem));
//        intent.putExtra("source", articlesItem.getSource());
//        startActivity(intent);
    }

    private void initRefreshListeners() {
        binding.swipeRefresh.setOnRefreshListener(() -> mViewModel.refreshData());
        binding.noInternetContainer.tryAgain.setOnClickListener(v -> mViewModel.refreshData());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initRecyclerView() {
        binding.categoryRV.setLayoutManager(verticalLayoutManager);
        binding.categoryRV.setAdapter(mainAdapter);
        binding.categoryItemsRV.setLayoutManager(horizontalLayoutManager);
        binding.categoryItemsRV.setAdapter(barangItemAdapter);
        barangItemAdapter.setViewModel(mViewModel);
        mainAdapter.setViewModel(mViewModel);

        binding.categoryRV.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(scrollY > oldScrollY)
                binding.categoryItemsRV.animate().translationY(-1000).setInterpolator(new AccelerateInterpolator()).start();
            else if(scrollY < oldScrollY || scrollY == 0)
                binding.categoryItemsRV.animate().translationY(0).setInterpolator(new AccelerateInterpolator()).start();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewModel.resetItemDetails();
    }

}
