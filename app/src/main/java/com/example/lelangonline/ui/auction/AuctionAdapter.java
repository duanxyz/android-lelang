package com.example.lelangonline.ui.auction;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.R;
import com.example.lelangonline.databinding.RvAuctionLayoutBinding;
import com.example.lelangonline.models.DataItem;

public class AuctionAdapter extends PagedListAdapter<DataItem, AuctionAdapter.MainViewHolder> {

    private RequestManager requestManager;
    private RequestOptions requestOptions;
    private AuctionViewModel auctionViewModel;


    public AuctionAdapter(RequestManager requestManager, RequestOptions requestOptions) {
        super(DataItem.CALLBACK);
        this.requestManager = requestManager;
        this.requestOptions = requestOptions;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_auction_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        if (getItem(position) != null)
            holder.bind(getItem(position));
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        private RvAuctionLayoutBinding binding;

        MainViewHolder(@NonNull RvAuctionLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        void bind(DataItem item) {
            binding.setDataItem(item);
            binding.setRequestManage(requestManager.setDefaultRequestOptions(requestOptions));
            binding.setViewModel(auctionViewModel);
            binding.setPosition(getAdapterPosition());
            binding.executePendingBindings();
        }
    }

    public void setViewModel(AuctionViewModel auctionViewModel) {
        this.auctionViewModel = auctionViewModel;
    }

}
