package com.example.lelangonline.ui.main.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.lelangonline.models.DataItem;
import com.example.lelangonline.models.balance.Balance;
import com.example.lelangonline.paging.main.home.BalanceDataSource;
import com.example.lelangonline.paging.main.home.BalanceDataSourceFactory;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.utils.DataStatus;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private MainRepository mainRepository;
    private LiveData<DataStatus> status;
    private PagedList.Config config;
    private BalanceDataSourceFactory factory;
    private LiveData<PagedList<Balance>> itemPagedList;
    private MutableLiveData<Balance> dataDetails;

    @Inject
    HomeViewModel(MainRepository mainRepository, PagedList.Config config, BalanceDataSourceFactory factory) {
        this.mainRepository = mainRepository;
        this.config = config;
        this.factory = factory;
        this.dataDetails = new MutableLiveData<>();
    }
    public void fetchData(){
        itemPagedList = new LivePagedListBuilder(factory, config).build();
        status = Transformations.switchMap(factory.getMutableLiveData(), BalanceDataSource::getMutableLiveData);
    }

    LiveData<String> getAvatar() {
        mainRepository.getAvatarImage();
        return mainRepository.getSelectedAvatarImage();
    }

    LiveData<String> Username() {
        mainRepository.getUsername();
        return mainRepository.getUserName();
    }
    LiveData<String> balance() {
        mainRepository.getBalance();
        return mainRepository.getbalance();
    }

    LiveData<DataStatus> getDataStatus() {
        return status;
    }

    LiveData<PagedList<Balance>> getItemPagedList() {
        return itemPagedList;
    }

    LiveData<Balance> observeDataDetails(){
        return dataDetails;
    }

    void resetdataDetails(){
        dataDetails.setValue(null);
    }

    void refreshData() {
        if (itemPagedList.getValue() != null) {
            itemPagedList.getValue().getDataSource().invalidate();
        }
    }

}