package com.example.lelangonline.ui.details;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lelangonline.models.saved.SavedBarang;

import javax.inject.Inject;

public class DetailsViewModel extends ViewModel {

    private MutableLiveData<Boolean> closeItem;
    private MutableLiveData<Boolean> openItemWebView;
    private MutableLiveData<Boolean> shareItem;
    private DetailsRepository detailsRepository;

    @Inject
    DetailsViewModel(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
        closeItem = new MutableLiveData<>();
        openItemWebView = new MutableLiveData<>();
        shareItem = new MutableLiveData<>();
    }

    public void closeArticle(){
        closeItem.setValue(true);
    }

//    public void openArticleWebView(){
//        openItemWebView.setValue(true);
//    }

    public void shareItem(){
        shareItem.setValue(true);
    }

    LiveData<Boolean> checkIfItemExist(int id){
        Log.d("TAG", "checkIfArticleExist: ");
        detailsRepository.checkItemExist(id);
        return detailsRepository.getItemExist();
    }

    public void saveItem(SavedBarang savedBarang){
        detailsRepository.itemStatus(savedBarang);
    }

    public LiveData<Boolean> getShareArticle() {
        return shareItem;
    }

    LiveData<Boolean> getCloseItem() {
        return closeItem;
    }

    LiveData<Boolean> getOpenArticleWebView() {
        return openItemWebView;
    }
}
