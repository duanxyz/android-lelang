package com.example.lelangonline.ui.main.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.lelangonline.models.DataItem;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.utils.DataStatus;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private MainRepository mainRepository;
    private LiveData<PagedList<DataItem>> itemPagedList;
    private PagedList.Config config;
    private LiveData<DataStatus> newsData;
    private MutableLiveData<DataItem> dataDetails;

    @Inject
    HomeViewModel(MainRepository mainRepository, PagedList.Config config) {
        this.mainRepository = mainRepository;
        this.config = config;
        this.dataDetails = new MutableLiveData<>();
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
        return newsData;
    }

    LiveData<PagedList<DataItem>> getItemPagedList() {
        return itemPagedList;
    }

    LiveData<DataItem> observeDataDetails(){
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