package com.example.lelangonline.ui.main.barang;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lelangonline.R;
import com.example.lelangonline.databinding.RvCategoryLayoutBinding;
import com.example.lelangonline.utils.Constants;

import java.util.List;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.CategoryViewHolder> {
    private BarangViewModel viewModel;
    private List<Pair<String, Integer>> itemCategoryList;
    private int selectedItem;

    public CategoryItemAdapter() {
        itemCategoryList = Constants.getCategoryList();
        selectedItem = 0;
    }

    @NonNull
    @Override
    public CategoryItemAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_category_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemAdapter.CategoryViewHolder holder, int position) {
        holder.bind(itemCategoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemCategoryList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private RvCategoryLayoutBinding binding;

        CategoryViewHolder(@NonNull RvCategoryLayoutBinding binding) {
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

    void setViewModel(BarangViewModel viewModel) {
        this.viewModel = viewModel;
    }

    void setPositionNum(int pos){
        this.selectedItem = pos;
        notifyDataSetChanged();
    }

}
