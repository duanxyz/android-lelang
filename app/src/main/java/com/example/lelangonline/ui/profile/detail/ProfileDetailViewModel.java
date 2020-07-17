package com.example.lelangonline.ui.profile.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lelangonline.models.users.DataItem;
import com.example.lelangonline.ui.MainRepository;

import javax.inject.Inject;

public class ProfileDetailViewModel extends ViewModel {
    private MutableLiveData<Boolean> closeItem;
    private MainRepository mainRepository;

    @Inject
    ProfileDetailViewModel(MainRepository mainRepository){
        this.mainRepository = mainRepository;
        closeItem = new MutableLiveData<>();
    }

    public void closeArticle(){
        closeItem.setValue(true);
    }

    LiveData<Boolean> getCloseItem() {
        return closeItem;
    }

    public void simpan(DataItem dataItem){
        mainRepository.ubahUser(dataItem);
    }
}
