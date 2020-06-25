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
import com.example.lelangonline.utils.DataStatus;

import javax.inject.Inject;

public class BarangViewModel extends ViewModel {

    private LiveData<PagedList<DataItem>> itemPagedList;
    private LiveData<DataStatus> dataStatus;
    private BarangDataSourceFactory factory;
    private PagedList.Config config;
    private MutableLiveData<Integer> selectedItem;
    private MutableLiveData<DataItem> dataDetails;

    @Inject
    public BarangViewModel(BarangDataSourceFactory factory, PagedList.Config config) {
        this.factory = factory;
        this.config = config;
        this.selectedItem = new MutableLiveData<>();
        this.dataDetails = new MutableLiveData<>();
        fetchBarangsData();
    }

    private void fetchBarangsData() {
        itemPagedList = new LivePagedListBuilder(factory, config).build();
        dataStatus = Transformations.switchMap(factory.getMutableLiveData(), BarangDataSource::getMutableLiveData);

    }

    public void resetItemDetails() {
        dataDetails.setValue(null);
    }

    public void refreshData() {
        if (itemPagedList.getValue() != null) {
            itemPagedList.getValue().getDataSource().invalidate();
        }
    }

    LiveData<DataItem> getBarangDetails() {
        return dataDetails;
    }

    LiveData<Integer> getSelectedItem() {
        return selectedItem;
    }

    LiveData<DataStatus> getDataStatus() {
        return dataStatus;
    }


    LiveData<PagedList<DataItem>> getItemPagedList() {
        return itemPagedList;
    }

//    public void fetchTopNewsData() {
//        itemPagedList = new LivePagedListBuilder(factory, config).build();
//        dataStatus = Transformations.switchMap(factory.getMutableLiveData(), BarangDataSource::getMutableLiveData);
//    }
    // TODO: Implement the ViewModel

    public void fetchCategoryNewsData(String category, int pos){
        selectedItem.setValue(pos);
        factory.setCategory(category);
        refreshData();
    }

    public void openBarangDetails(DataItem dataItem){
        dataDetails.setValue(dataItem);
    }
}
