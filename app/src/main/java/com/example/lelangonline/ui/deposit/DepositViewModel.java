package com.example.lelangonline.ui.deposit;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.lelangonline.R;
import com.example.lelangonline.models.auction.DataItem;
import com.example.lelangonline.models.banks.Banks;
import com.example.lelangonline.paging.Banks.BanksDataSource;
import com.example.lelangonline.paging.Banks.BanksDataSourceFactory;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.utils.DataStatus;

import java.util.List;

import javax.inject.Inject;

public class DepositViewModel extends ViewModel {
    private MainRepository mainRepository;
    private MutableLiveData<List<Banks>> banks;
    private LiveData<PagedList<Banks>> itemPagedList;
    private LiveData<DataStatus> banksData;
    private PagedList.Config config;
    private BanksDataSourceFactory factory;
    private MutableLiveData<Banks> bankInfo;
    private MutableLiveData<Boolean> closeItem;



    @Inject
    DepositViewModel(MainRepository mainRepository, PagedList.Config config, BanksDataSourceFactory factory){
        this.mainRepository = mainRepository;
        this.factory = factory;
        this.config = config;
        this.banks = new MutableLiveData<List<Banks>>();
        this.bankInfo = new MutableLiveData<>();
        closeItem = new MutableLiveData<>();
    }

    public void fetchBanks(){
        itemPagedList = new LivePagedListBuilder(factory, config).build();
        banksData = Transformations.switchMap(factory.getMutableLiveData(), BanksDataSource::getMutableLiveData);
    }

    LiveData<DataStatus> getStatus() { return banksData; }

    LiveData<PagedList<Banks>> getItemPagedList() {
        return itemPagedList;
    }

    public void openBank(Banks banks){
        bankInfo.postValue(banks);
    }

    LiveData<Banks> observeBankInfo(){
        return bankInfo;
    }

    public void close(){
        closeItem.setValue(true);
    }

    LiveData<Boolean> getCloseItem() {
        return closeItem;
    }
}
