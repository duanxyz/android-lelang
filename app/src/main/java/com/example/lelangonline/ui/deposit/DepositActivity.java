package com.example.lelangonline.ui.deposit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.ActivityDepositBinding;
import com.example.lelangonline.models.banks.Banks;
import com.example.lelangonline.ui.deposit.transfer.TransferActivity;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerAppCompatActivity;

public class DepositActivity extends DaggerAppCompatActivity {

    private DepositViewModel viewModel;
    private ActivityDepositBinding binding;

    @Inject
    BankTransferAdapter transferAdapter;
    @Inject
    ViewModelProviderFactory factory;
    @Inject
    LinearLayoutManager layoutManager;
//    @Named("circleRequestOption")
//    @Inject
//    RequestOptions requestOptions;
//    @Inject
//    RequestManager requestManager;
//    @Inject
//    ConnectivityManager connectivityManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_deposit);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this, factory).get(DepositViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.fetchBanks();
        initRecyclersView();
        observeObservers();
    }

    private void observeObservers() {
        viewModel.getItemPagedList().observe(this, banks -> {
            if (banks != null){
                transferAdapter.submitList(banks);
                binding.bankRV.scrollToPosition(0);
            }
        });

        viewModel.getStatus().observe(this, dataStatus -> {
            if (dataStatus != null) {
                switch (dataStatus) {
                    case ERROR:
//                        stopSwipeRefresh();
//                        stopShimmer();
                        binding.bankRV.setVisibility(View.INVISIBLE);
//                        binding.noInternetContainer.getRoot().setVisibility(View.VISIBLE);
                        break;
                    case LOADING:
//                        binding.shimmerLayout.startShimmer();
                        break;
                    case LOADED:
//                        stopSwipeRefresh();
//                        stopShimmer();
                        binding.bankRV.setVisibility(View.VISIBLE);
//                        viewModel.getAuction(String.valueOf(id));

//                        binding.noInternetContainer.getRoot().setVisibility(View.GONE);
//                    case EMPTY:
//                        Toast.makeText(getActivity(), "Field is empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        viewModel.observeBankInfo().observe(this, banks -> {
            if (banks != null){
                openBankInfo(banks);
            }
        });

        viewModel.getCloseItem().observe(this, close -> {
            if (close)
                finish();
        });
    }

    private void openBankInfo(Banks banks) {
        Intent intent = new Intent(getApplication(), TransferActivity.class);
        intent.putExtra("bank", banks);
        startActivity(intent);
    }

    private void initRecyclersView() {
        transferAdapter.setViewModel(viewModel);
        binding.bankRV.setLayoutManager(layoutManager);
        binding.bankRV.setAdapter(transferAdapter);
    }
}
