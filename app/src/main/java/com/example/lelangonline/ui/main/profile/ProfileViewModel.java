package com.example.lelangonline.ui.main.profile;

import android.util.Log;

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
    private MutableLiveData<DataItem> profileDetails;
    private MutableLiveData<String> status;
    private MutableLiveData<Boolean> seisson;

    @Inject
    ProfileViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
        this.userData = new MutableLiveData<>();
        this.profileDetails = new MutableLiveData<>();
        this.status = new MutableLiveData<>();
        this.seisson = new MutableLiveData<>();
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
        profileDetails.setValue(dataItem);
        status.setValue("open");
    }

    public LiveData<DataItem> observeProfileDetails() {
        return profileDetails;
    }

    public LiveData<String> observeStatus() {
        return status;
    }

    public void logout(){
        mainRepository.resetStored();
        seisson.setValue(mainRepository.getSeisson().getValue());
    }

    public LiveData<Boolean> getSeisson(){
        return seisson;
    }

//    void refreshData() {
//        if (userData.getValue() != null) {
//            userData.getValue();
//        }
//    }
}
