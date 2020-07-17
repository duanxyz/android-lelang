package com.example.lelangonline.ui.withdraw;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WithdrawViewModel extends ViewModel {
    public MutableLiveData<String> rekening = new MutableLiveData<>();
    public MutableLiveData<String> nama = new MutableLiveData<>();
    public MutableLiveData<String> jumlah = new MutableLiveData<>();

}
