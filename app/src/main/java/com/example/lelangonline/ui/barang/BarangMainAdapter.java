package com.example.lelangonline.ui.barang;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.lelangonline.R;
import com.example.lelangonline.databinding.RvCategoryMainLayoutBinding;
import com.example.lelangonline.models.DataItem;

public class BarangMainAdapter extends PagedListAdapter<DataItem, BarangMainAdapter.MainViewHolder> {

    private RequestManager requestManager;
    private RequestOptions requestOptions;
    private BarangViewModel barangViewModel;

    public BarangMainAdapter(RequestManager requestManager, RequestOptions requestOptions) {
        super(DataItem.CALLBACK);
        this.requestManager = requestManager;
        this.requestOptions = requestOptions;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_category_main_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        if (getItem(position) != null)
            holder.bind(getItem(position));
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public void setViewModel(BarangViewModel mViewModel) {
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        private RvCategoryMainLayoutBinding binding;

        MainViewHolder(@NonNull RvCategoryMainLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        void bind(DataItem item) {
            binding.setDataItem(item);
            binding.setRequestManage(requestManager.setDefaultRequestOptions(requestOptions));
            binding.setViewModel(barangViewModel);
            binding.setPosition(getAdapterPosition());
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)binding.cardView.getLayoutParams();
            if(getAdapterPosition() == 0)
                params.setMargins(8, 144, 8, 8);
            else
                params.setMargins(8, 4, 8, 8);

            binding.cardView.setLayoutParams(params);
            binding.executePendingBindings();
        }

    }
}
