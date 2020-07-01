package com.example.lelangonline.ui.barang;

import android.util.Log;

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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class BarangViewModel extends ViewModel {

    private MainRepository mainRepository;
    private LiveData<PagedList<DataItem>> itemPagedList;
    private BarangDataSourceFactory barangFactory;
    private PagedList.Config config;
    private MutableLiveData<DataStatus> dataStatus;
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

    void fetchBarangData(){
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
    }

    LiveData<Integer> getSelectedItem() {
        return selectedItem;
    }

    public void fetchCategoryNewsData(String category, int pos){
        selectedItem.setValue(pos);
        barangFactory.setCategory(category);
        refreshData();
    }

    public void searchArticles(String search){
        Log.d("TAG", "searchArticles: ");
        if(search.equals("") || search.length() == 0)
            dataStatus.setValue(DataStatus.EMPTY);
        else {
            barangFactory.setSearch(search);
            refreshData();
        }
    }
}
