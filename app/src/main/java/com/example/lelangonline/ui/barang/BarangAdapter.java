package com.example.lelangonline.ui.barang;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.R;
import com.example.lelangonline.databinding.RvHomeLayoutBinding;
import com.example.lelangonline.models.DataItem;

public class BarangAdapter extends PagedListAdapter<DataItem, BarangAdapter.MainViewHolder> {

    private RequestManager requestManager;
    private RequestOptions requestOptions;
    private BarangViewModel barangViewModel;


    public BarangAdapter(RequestManager requestManager, RequestOptions requestOptions) {
        super(DataItem.CALLBACK);
        this.requestManager = requestManager;
        this.requestOptions = requestOptions;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_home_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        if (getItem(position) != null)
            holder.bind(getItem(position));
    }


    class MainViewHolder extends RecyclerView.ViewHolder {
        private RvHomeLayoutBinding binding;

        MainViewHolder(@NonNull RvHomeLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        void bind(DataItem item) {
            binding.setDataItem(item);
            binding.setRequestManage(requestManager.setDefaultRequestOptions(requestOptions));
            binding.setViewModel(barangViewModel);
            binding.setPosition(getAdapterPosition());
            binding.executePendingBindings();
        }

    }

    public void setViewModel(BarangViewModel barangViewModel) {
        this.barangViewModel = barangViewModel;
    }

}
