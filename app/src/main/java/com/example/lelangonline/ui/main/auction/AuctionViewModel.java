package com.example.lelangonline.ui.main.auction;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.lelangonline.models.DataItem;
import com.example.lelangonline.paging.main.barang.BarangDataSource;
import com.example.lelangonline.paging.main.barang.BarangDataSourceFactory;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.utils.DataStatus;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.inject.Inject;

public class AuctionViewModel extends ViewModel {
    // TODO: Implement the

    private MainRepository mainRepository;
    private LiveData<PagedList<DataItem>> itemPagedList;
    private BarangDataSourceFactory barangFactory;
    private PagedList.Config config;
    private MutableLiveData<DataStatus> dataStatus;
    private LiveData<DataStatus> newsData;
    private MutableLiveData<DataItem> barangDetails;
    private MutableLiveData<Integer> selectedItem;

    @Inject
    AuctionViewModel(MainRepository mainRepository, PagedList.Config config, BarangDataSourceFactory barangFactory) {
        this.mainRepository = mainRepository;
        this.barangFactory = barangFactory;
        this.config = config;
        this.selectedItem = new MutableLiveData<>();
        this.barangDetails = new MutableLiveData<>();
    }

    public void fetchAuctionData() {
        itemPagedList = new LivePagedListBuilder(barangFactory, config).build();
        newsData = Transformations.switchMap(barangFactory.getMutableLiveData(), BarangDataSource::getMutableLiveData);
    }

    LiveData<DataItem> observeAuctionDetails(){
        return barangDetails;
    }

//    public String initialPrice(int price){
//        NumberFormat formatter = new DecimalFormat("#,###");
//        String formattedNumber = formatter.format(price);
//        return formattedNumber;
//    }

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

    public void resetItemDetails() {barangDetails.setValue(null);}

    public void fetchAuctionItem(String currentDateandTime) {
        barangFactory.setDate(currentDateandTime);
    }

    public void openAuction(DataItem dataItem){
        barangDetails.setValue(dataItem);
    }

}
