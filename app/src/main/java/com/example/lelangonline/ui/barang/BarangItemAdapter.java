package com.example.lelangonline.ui.barang;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lelangonline.R;
import com.example.lelangonline.databinding.RvCategoryLayoutBinding;
import com.example.lelangonline.utils.Constants;

import java.util.List;

public class BarangItemAdapter extends RecyclerView.Adapter<BarangItemAdapter.BarangViewHolder> {

    private BarangViewModel viewModel;
    private List<Pair<String, Integer>> barangCategoryList;
    private int selectedItem;

    public BarangItemAdapter() {
        barangCategoryList = Constants.getCategoryList();
        selectedItem = 0;
    }

    @NonNull
    @Override
    public BarangItemAdapter.BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BarangViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_category_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BarangItemAdapter.BarangViewHolder holder, int position) {
        holder.bind(barangCategoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return barangCategoryList.size();
    }

    void setViewModel(BarangViewModel viewModel) {
        this.viewModel = viewModel;
    }

    void setPositionNum(int pos){
        this.selectedItem = pos;
        notifyDataSetChanged();
    }

    public class BarangViewHolder extends RecyclerView.ViewHolder {
        private RvCategoryLayoutBinding binding;

        BarangViewHolder(@NonNull RvCategoryLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Pair<String, Integer> category) {
            binding.setViewModel(viewModel);
            binding.setCategory(category);
            binding.setPosition(getAdapterPosition());
            binding.setSelected(selectedItem);
            binding.executePendingBindings();
        }
    }
}
