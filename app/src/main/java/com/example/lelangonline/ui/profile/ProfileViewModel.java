package com.example.lelangonline.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lelangonline.models.users.DataItem;
import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.utils.DataStatus;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private MainRepository mainRepository;
    private MutableLiveData<DataItem> userData;
    private MutableLiveData<DataStatus> dataStatus;

    @Inject
    ProfileViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
        this.userData = new MutableLiveData<>();
        mainRepository.fetchFromApi();
    }

    LiveData<String> getAvatar(){
        mainRepository.getAvatarImage();
        return mainRepository.getSelectedAvatarImage();
    }

    public MutableLiveData<DataStatus> getMembers(){
//        mainRepository.fetchFromApi();
        userData.postValue(mainRepository.getUserFromDB().getValue());
        return mainRepository.getMutableLiveData();
    }

    public LiveData<DataItem> fetchMember() {
        return userData;
    }

    public void openProfile(DataItem dataItem){
        userData.setValue(dataItem);
    }


//    void refreshData() {
//        if (userData.getValue() != null) {
//            userData.getValue();
//        }
//    }
}
