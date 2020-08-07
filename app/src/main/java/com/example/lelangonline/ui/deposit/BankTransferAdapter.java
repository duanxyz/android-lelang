package com.example.lelangonline.ui.deposit;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.R;
import com.example.lelangonline.databinding.RvBanksLayoutBinding;
import com.example.lelangonline.models.banks.Banks;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BankTransferAdapter extends PagedListAdapter<Banks, BankTransferAdapter.BankViewHolder> {
    private DepositViewModel viewModel;
    private RequestManager requestManager;
    private RequestOptions requestOptions;

    public BankTransferAdapter(){
        super(Banks.CALLBACK);
//        this.requestManager = requestManager;
//        this.requestOptions = requestOptions;
    }


    @NonNull
    @Override
    public BankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BankViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_banks_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BankViewHolder holder, int position) {
        if (getItem(position) != null)
            holder.bind(getItem(position));
    }

    public class BankViewHolder extends RecyclerView.ViewHolder {
        private RvBanksLayoutBinding binding;

        BankViewHolder(@NonNull RvBanksLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Banks banks){
            binding.setBank(banks);
//            binding.setRequestManage(requestManager.setDefaultRequestOptions(requestOptions));
            binding.setViewModel(viewModel);
            binding.setPosition(getAdapterPosition());
            binding.executePendingBindings();
        }
    }

    public void setViewModel(DepositViewModel viewModel){
        this.viewModel = viewModel;
    }
}
