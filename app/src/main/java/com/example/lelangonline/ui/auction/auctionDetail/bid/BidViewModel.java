package com.example.lelangonline.ui.auction.auctionDetail.bid;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.lelangonline.models.auction.DataItem;
import com.example.lelangonline.paging.auction.AuctionDataSource;
import com.example.lelangonline.paging.auction.AuctionDataSourceFactory;
import com.example.lelangonline.paging.main.barang.BarangDataSourceFactory;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.ui.auction.auctionDetail.AuctionDetailRepository;
import com.example.lelangonline.utils.DataStatus;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.inject.Inject;

public class BidViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private AuctionDetailRepository repository;
    private LiveData<PagedList<DataItem>> itemPagedList;
    private AuctionDataSourceFactory factory;
    private PagedList.Config config;
    private MutableLiveData<DataStatus> dataStatus;
    private LiveData<DataStatus> newsData;
    private MutableLiveData<DataItem> auctionDetails;
    private MutableLiveData<Integer> selectedItem;

    @Inject
    BidViewModel(AuctionDetailRepository repository, PagedList.Config config, AuctionDataSourceFactory factory) {
        this.repository = repository;
        this.factory = factory;
        this.config = config;
        this.selectedItem = new MutableLiveData<>();
        this.auctionDetails = new MutableLiveData<>();
    }

    public void fetchAuctionData() {
        itemPagedList = new LivePagedListBuilder(factory, config).build();
        newsData = Transformations.switchMap(factory.getMutableLiveData(), AuctionDataSource::getMutableLiveData);
    }

    LiveData<DataItem> observeAuctionDetails(){
        return auctionDetails;
    }

    public String initialPrice(int price){
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(price);
        return formattedNumber;
    }

    public void refreshData() {
        if (itemPagedList.getValue() != null) {
            itemPagedList.getValue().getDataSource().invalidate();
        }
    }

    LiveData<PagedList<DataItem>> getItemPagedList() {
        return itemPagedList;
    }

    LiveData<DataStatus> getDataStatus() {
        return newsData;
    }

    public void resetItemDetails() {auctionDetails.setValue(null);}

    public void getAuction(String item_id){
        Log.d("TAG", "getAuction: ");
        if(item_id.equals("") || item_id.length() == 0)
            dataStatus.setValue(DataStatus.EMPTY);
        else {
            factory.setItemId(item_id);
            refreshData();
        }
    }

    public String offer(int offer){
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = "Rp. "+formatter.format(offer);
        return formattedNumber;
    }


//    public void openAuction(DataItem dataItem){
//        auctionDetails.setValue(dataItem);
//    }

}
