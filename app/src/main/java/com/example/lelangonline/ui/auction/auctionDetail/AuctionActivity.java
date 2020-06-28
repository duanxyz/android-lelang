package com.example.lelangonline.ui.auction.auctionDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bumptech.glide.RequestManager;
import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.ActivityAuctionBinding;
import com.example.lelangonline.models.saved.SavedBarang;
import com.example.lelangonline.ui.details.DetailsViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuctionActivity extends DaggerAppCompatActivity {

    private AuctionDetailViewModel viewModel;
    private ActivityAuctionBinding binding;
    private EditText tawar;


    @Inject
    RequestManager requestManager;

    @Inject
    ViewModelProviderFactory providerFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auction);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this, providerFactory).get(AuctionDetailViewModel.class);
        SavedBarang dataItem = getIntent().getExtras().getParcelable("item");
//        Source source = getIntent().getExtras().getParcelable("source");

        binding.setViewModel(viewModel);
        binding.setReqManager(requestManager);
        binding.setItem(dataItem);
//        binding.setSource(source);
        viewModel.checkIfItemExist(dataItem.getId());
        observeObservers(dataItem.getId());

    }

    private void observeObservers(int id) {

//        viewModel.checkIfItemExist(id).observe(this, isExist -> {
//            if (isExist)
//                binding.saveArticle.setImageResource(R.drawable.ic_bookmark_blue);
//            else
//                binding.saveArticle.setImageResource(R.drawable.ic_bookmark_open);
//        });

//        viewModel.getShareArticle().observe(this, share -> {
//            if (share)
//                shareArticle(title, link);
//        });

        viewModel.getCloseItem().observe(this, close -> {
            if (close)
                finish();
        });

    }
}
