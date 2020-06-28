package com.example.lelangonline.ui.auction.auctionDetail.bid;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.R;
import com.example.lelangonline.databinding.RvAuctionDetailsLayoutBinding;
import com.example.lelangonline.models.auction.DataItem;
import com.example.lelangonline.ui.auction.auctionDetail.AuctionDetailViewModel;

public class BidAdapter extends PagedListAdapter<DataItem, BidAdapter.MainViewHolder> {

    private AuctionDetailViewModel bidViewModel;
    public BidAdapter() {
        super(DataItem.CALLBACK);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_auction_details_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        if (getItem(position) != null)
            holder.bind(getItem(position));
    }


    public class MainViewHolder extends RecyclerView.ViewHolder {
        private RvAuctionDetailsLayoutBinding binding;

        MainViewHolder(@NonNull RvAuctionDetailsLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        void bind(DataItem item) {
            binding.setDataItem(item);
            binding.setViewModel(bidViewModel);
            binding.setPosition(getAdapterPosition());
            binding.executePendingBindings();
        }
    }
    public void setViewModel(AuctionDetailViewModel bidViewModel) {
        this.bidViewModel = bidViewModel;
    }
}
