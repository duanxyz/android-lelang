package com.example.lelangonline.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.R;
import com.example.lelangonline.models.DataItem;

public class HomeAdapter {

    private RequestManager requestManager;
    private RequestOptions requestOptions;
    private HomeViewModel homeViewModel;

    public HomeAdapter(RequestManager requestManager, RequestOptions requestOptions) {
//        super(DataItem.CALLBACK);
        this.requestManager = requestManager;
        this.requestOptions = requestOptions;
    }

//    @NonNull
//    @Override
//    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new MainViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
//                R.layout.cell_title_layout, parent, false));
//    }
//
//
//    @Override
//    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
//        if (getItem(position) != null)
//            holder.bind(getItem(position));
//    }
//
//    class MainViewHolder extends RecyclerView.ViewHolder {
////        private RvHomeLayoutBinding binding;
//
//        MainViewHolder(@NonNull RvHomeLayoutBinding itemView) {
//            super(itemView.getRoot());
//            binding = itemView;
//        }
//
//        void bind(DataItem dataItem) {
//            binding.setArticlesItem(dataItem);
//            binding.setRequestManage(requestManager.setDefaultRequestOptions(requestOptions));
//            binding.setViewModel(homeViewModel);
//            binding.setPosition(getAdapterPosition());
//            binding.executePendingBindings();
//        }
//
//    }

    public void setViewModel(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
    }
}
