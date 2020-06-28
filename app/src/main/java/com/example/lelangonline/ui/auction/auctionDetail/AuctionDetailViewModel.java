package com.example.lelangonline.ui.auction.auctionDetail;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lelangonline.models.auction.Auction;
import com.example.lelangonline.models.auction.DataItem;
import com.example.lelangonline.utils.Constants;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.inject.Inject;

public class AuctionDetailViewModel extends ViewModel {

    private SharedPreferences preferences;
    private MutableLiveData<Boolean> closeItem;
    private AuctionDetailRepository auctionDetailRepository;
    public MutableLiveData<String> Tawaran = new MutableLiveData<>();


    @Inject
    AuctionDetailViewModel(AuctionDetailRepository auctionDetailRepository, SharedPreferences preferences) {
        this.auctionDetailRepository = auctionDetailRepository;
        this.preferences = preferences;
        closeItem = new MutableLiveData<>();
    }

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
        String formattedNumber = "Harga Awal : Rp."+formatter.format(price);
        return formattedNumber;

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
