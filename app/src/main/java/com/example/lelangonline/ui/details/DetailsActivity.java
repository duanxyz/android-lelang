package com.example.lelangonline.ui.details;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.RequestManager;
import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.ActivityDetailsBinding;
import com.example.lelangonline.models.saved.SavedBarang;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DetailsActivity extends DaggerAppCompatActivity {

    private DetailsViewModel viewModel;
    private ActivityDetailsBinding binding;

    @Inject
    RequestManager requestManager;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this, providerFactory).get(DetailsViewModel.class);
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

        viewModel.checkIfItemExist(id).observe(this, isExist-> {
            if(isExist)
                binding.saveArticle.setImageResource(R.drawable.ic_bookmark_blue);
            else
                binding.saveArticle.setImageResource(R.drawable.ic_bookmark_open);
        });

//        viewModel.getShareArticle().observe(this, share -> {
//            if (share)
//                shareArticle(title, link);
//        });

        viewModel.getCloseItem().observe(this, close -> {
            if (close)
                finish();
        });

//        viewModel.getOpenArticleWebView().observe(this, openWebView -> {
//            if (openWebView)
//                openWebView(link);
//
//        });
    }

//    private void openWebView(String link) {
//        getSupportFragmentManager().beginTransaction()
//                .add(android.R.id.content, new WebViewFragment(link))
//                .addToBackStack(null)
//                .commit();
//    }


//    private void shareArticle(String title, String link) {
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_TEXT, title + "\n\n" + link);
//        startActivity(Intent.createChooser(intent, "Dialog title text"));
//    }
}
