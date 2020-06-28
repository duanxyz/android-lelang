package com.example.lelangonline.paging.auction;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.lelangonline.models.auction.DataItem;
import com.example.lelangonline.ui.auction.auctionDetail.AuctionDetailRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class AuctionDataSourceFactory extends DataSource.Factory {

    private AuctionDetailRepository repository;
    private CompositeDisposable disposable;
    private String item_id;
    private MutableLiveData<AuctionDataSource> mutableLiveData;

    @Inject
    public AuctionDataSourceFactory(AuctionDetailRepository repository, CompositeDisposable disposable) {
        this.repository = repository;
        this.disposable = disposable;
        this.item_id = "";
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer , DataItem> create() {
        AuctionDataSource dataSource = new AuctionDataSource(disposable, repository, item_id);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<AuctionDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setItemId(String item_id) {
        this.item_id = item_id;
    }
}
