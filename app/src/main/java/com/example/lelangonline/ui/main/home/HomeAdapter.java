package com.example.lelangonline.ui.main.home;

<<<<<<< HEAD
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lelangonline.R;
import com.example.lelangonline.databinding.RvTransactionLayoutBinding;
import com.example.lelangonline.models.balance.Balance;
import com.example.lelangonline.ui.main.barang.BarangViewModel;

public class HomeAdapter extends PagedListAdapter<Balance, HomeAdapter.MainViewHolder> {

    private HomeViewModel homeViewModel;

    public HomeAdapter(){ super(Balance.CALLBACK);}

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_transaction_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        if (getItem(position) != null)
            holder.bind(getItem(position));
    }

    class MainViewHolder extends RecyclerView.ViewHolder{
        private RvTransactionLayoutBinding binding;

        MainViewHolder(@NonNull RvTransactionLayoutBinding layoutBinding){
            super(layoutBinding.getRoot());
            binding = layoutBinding;
        }

        void bind(Balance balance){
            binding.setBalance(balance);
            binding.setViewModel(homeViewModel);
            binding.setPosition(getAdapterPosition());
            binding.executePendingBindings();
        }
    }

    public void setViewModel(HomeViewModel viewModel) {
        this.homeViewModel = viewModel;
    }

=======
class HomeAdapter {
>>>>>>> b1ad87c... error
}
