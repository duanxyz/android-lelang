package com.example.lelangonline.ui.barang;

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

public class BarangViewModel extends ViewModel {

    private MainRepository mainRepository;
    private LiveData<PagedList<DataItem>> itemPagedList;
    private BarangDataSourceFactory barangFactory;
    private PagedList.Config config;
    private LiveData<DataStatus> newsData;
    private MutableLiveData<DataItem> barangDetails;
    private MutableLiveData<Integer> selectedItem;



    @Inject
    BarangViewModel(MainRepository mainRepository, PagedList.Config config, BarangDataSourceFactory barangFactory) {
        this.mainRepository = mainRepository;
        this.barangFactory = barangFactory;
        this.config = config;
        this.selectedItem = new MutableLiveData<>();
        this.barangDetails = new MutableLiveData<>();
    }

    void fetchTopNewsData(){
        itemPagedList = new LivePagedListBuilder(barangFactory, config).build();
        newsData = Transformations.switchMap(barangFactory.getMutableLiveData(), BarangDataSource::getMutableLiveData);
    }

    void refreshData() {
        if (itemPagedList.getValue() != null) {
            itemPagedList.getValue().getDataSource().invalidate();
        }
    }

    public void openArticleDetails(DataItem dataItem){
        barangDetails.setValue(dataItem);
    }

    LiveData<DataItem> observeArticleDetails(){
        return barangDetails;
    }

    void resetArticleDetails(){
        barangDetails.setValue(null);
    }

    LiveData<String> getCountry() {
        mainRepository.getAvatarImage();
        return mainRepository.getSelectedAvatarImage();
    }

    LiveData<DataStatus> getDataStatus() {
        return newsData;
    }

    LiveData<PagedList<DataItem>> getItemPagedList() {
        return itemPagedList;
    }

    public String initialPrice(int price){
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(price);
        return formattedNumber;
//formattedNumber is equal to 1,000,000
    }

    LiveData<Integer> getSelectedItem() {
        return selectedItem;
    }

    public void fetchCategoryNewsData(String category, int pos){
        selectedItem.setValue(pos);
        barangFactory.setCategory(category);
        refreshData();
    }


//    private LiveData<PagedList<DataItem>> itemPagedList;
//    private LiveData<DataStatus> dataStatus;
//    private BarangDataSourceFactory factory;
//    private PagedList.Config config;
//    private MutableLiveData<Integer> selectedItem;
//    private MutableLiveData<DataItem> dataDetails;
//
//    @Inject
//    public BarangViewModel(BarangDataSourceFactory factory, PagedList.Config config) {
//        this.factory = factory;
//        this.config = config;
//        this.selectedItem = new MutableLiveData<>();
//        this.dataDetails = new MutableLiveData<>();
//        fetchBarangsData();
//    }
//
//    private void fetchBarangsData() {
//        itemPagedList = new LivePagedListBuilder(factory, config).build();
//        dataStatus = Transformations.switchMap(factory.getMutableLiveData(), BarangDataSource::getMutableLiveData);
//
//    }
//
//    public void resetItemDetails() {
//        dataDetails.setValue(null);
//    }
//
//    public void refreshData() {
//        if (itemPagedList.getValue() != null) {
//            itemPagedList.getValue().getDataSource().invalidate();
//        }
//    }
//
//    LiveData<DataItem> getBarangDetails() {
//        return dataDetails;
//    }
//
//    LiveData<Integer> getSelectedItem() {
//        return selectedItem;
//    }
//
//    LiveData<DataStatus> getDataStatus() {
//        return dataStatus;
//    }
//
//
//    LiveData<PagedList<DataItem>> getItemPagedList() {
//        return itemPagedList;
//    }
//
//    public void fetchCategoryNewsData(String category, int pos){
//        selectedItem.setValue(pos);
//        factory.setCategory(category);
//        refreshData();
//    }
//
//    public void openBarangDetails(DataItem dataItem){
//        dataDetails.setValue(dataItem);
//    }

}
