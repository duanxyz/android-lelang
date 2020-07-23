package com.example.lelangonline.ui.auctionDetail;

import android.content.SharedPreferences;
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
import com.example.lelangonline.utils.Constants;
import com.example.lelangonline.utils.DataStatus;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.inject.Inject;

public class AuctionDetailViewModel extends ViewModel {

    private SharedPreferences preferences;
    private MutableLiveData<Boolean> closeItem;
    private AuctionDetailRepository auctionDetailRepository;
    public MutableLiveData<String> Tawaran = new MutableLiveData<>();
    private AuctionDataSourceFactory factory;
    private PagedList.Config config;
    private MutableLiveData<DataItem> auctionDetails;
    private LiveData<PagedList<DataItem>> itemPagedList;
    private MutableLiveData<DataStatus> dataStatus;
    private LiveData<DataStatus> newsData;

    @Inject
    AuctionDetailViewModel(AuctionDetailRepository auctionDetailRepository, SharedPreferences preferences,
                           PagedList.Config config, AuctionDataSourceFactory factory) {
        this.auctionDetailRepository = auctionDetailRepository;
        this.preferences = preferences;
        this.factory = factory;
        this.config = config;
        this.auctionDetails = new MutableLiveData<>();
        closeItem = new MutableLiveData<>();
    }

    public void fetchAuctionData() {
        itemPagedList = new LivePagedListBuilder(factory, config).build();
        newsData = Transformations.switchMap(factory.getMutableLiveData(), AuctionDataSource::getMutableLiveData);
    }

    LiveData<DataItem> observeAuctionDetails(){
        return auctionDetails;
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

//    public String offer(int offer){
//        NumberFormat formatter = new DecimalFormat("#,###");
//        String formattedNumber = ": Rp. "+formatter.format(offer);
//        return formattedNumber;
//    }


    public void closeAuction(){
        closeItem.setValue(true);
    }

    LiveData<Boolean> checkIfItemExist(int id){
        Log.d("TAG", "checkIfArticleExist: ");
        auctionDetailRepository.checkItemExist(id);
        return auctionDetailRepository.getItemExist();
    }

    public String initialPrice(int price){
        NumberFormat formatter = new DecimalFormat("#,###");
        return String.format("Harga Awal : Rp.%s", formatter.format(price));

    }
    LiveData<Boolean> getCloseItem() {
        return closeItem;
    }

    public void btnSeribu(){
        int tmp;
        if (Tawaran.getValue() == null){
            tmp = 0;
        }else {
            tmp = Integer.parseInt(Tawaran.getValue());
        }
        Tawaran.setValue(String.valueOf(tmp + 1000));
    }

    public void btnPuluhan(){
        int tmp;
        if (Tawaran.getValue() == null){
            tmp = 0;
        }else {
            tmp = Integer.parseInt(Tawaran.getValue());
        }
        Tawaran.setValue(String.valueOf(tmp + 10000));
    }

    public void btnLimaPuluhan(){
        int tmp;
        if (Tawaran.getValue() == null){
            tmp = 0;
        }else {
            tmp = Integer.parseInt(Tawaran.getValue());
        }
        Tawaran.setValue(String.valueOf(tmp + 50000));
    }

    public void onTawar(int id){
        DataItem auction = new DataItem();
        auction.setItemId(String.valueOf(id));
        auction.setOffer(Integer.parseInt(Tawaran.getValue()));
        auction.setBidderUsername(preferences.getString(Constants.NAME_PREFS,""));
        auctionDetailRepository.postAuction(auction);
    }

}
