package com.example.lelangonline.ui.home;

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

//    void fetchTopNewsData() {
//        itemPagedList = new LivePagedListBuilder(homeFactory, config).build();
//        newsData = Transformations.switchMap(homeFactory.getMutableLiveData(), HomeDataSource::getMutableLiveData);
//    }

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

    public void openDataDetails(DataItem dataItem){
        dataDetails.setValue(dataItem);
    }


    void refreshData() {
        if (itemPagedList.getValue() != null) {
            itemPagedList.getValue().getDataSource().invalidate();
        }
    }

}