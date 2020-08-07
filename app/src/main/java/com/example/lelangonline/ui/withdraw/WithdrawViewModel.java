package com.example.lelangonline.ui.withdraw;

import android.text.Editable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lelangonline.models.banks.Banks;
import com.example.lelangonline.ui.MainRepository;

import javax.inject.Inject;

public class WithdrawViewModel extends ViewModel {
    public MutableLiveData<String> rekening = new MutableLiveData<>();
    public MutableLiveData<String> nama = new MutableLiveData<>();
    public MutableLiveData<String> jumlah = new MutableLiveData<>();
    private MutableLiveData<Boolean> closeItem;
    public MutableLiveData<Integer> nominal = new MutableLiveData<>();
    public MutableLiveData<String> txtNominal = new MutableLiveData<>();
    public MutableLiveData<String> operator = new MutableLiveData<>();
    public MutableLiveData<Banks> banks = new MutableLiveData<>();
    public MainRepository mainRepository;

    @Inject
    WithdrawViewModel(MainRepository mainRepository){
        this.mainRepository = mainRepository;
        closeItem = new MutableLiveData<>();
    }

    public void close(){
        closeItem.setValue(true);
    }

    LiveData<Boolean> getCloseItem() {
        return closeItem;
    }

    public void setBank(Editable nameBank, Editable accountNumber, Editable receiver) {
        Banks banks1 = new Banks();
        banks1.setBankName(String.valueOf(nameBank));
        banks1.setAccountNumber(String.valueOf(accountNumber));
        banks1.setName(String.valueOf(receiver));
        mainRepository.saveBanks(banks1);
    }

    public void setNominal(String number){
        final boolean hapus = !number.equals("0") && !number.equals("000") && !number.equals("hapus");

        if (nominal.getValue() == null){
            nominal.setValue(0);
            if (hapus)
                nominal.setValue(Integer.valueOf(String.valueOf(nominal.getValue())+number));
        }else if (hapus){
            nominal.setValue(Integer.valueOf(String.valueOf(nominal.getValue())+number));
        }else if (number.equals("0")){
            nominal.setValue(Integer.valueOf(String.valueOf(nominal.getValue())+number));
        }else if (number.equals("000")){
            nominal.setValue(Integer.valueOf(String.valueOf(nominal.getValue())+number));
        }else {
            String value = String.valueOf(nominal.getValue());
            if (value.length() > 1){
                nominal.setValue(Integer.valueOf(value.substring(0, value.length() - 1)));
            }else {
                nominal.setValue(0);
            }
        }
        txtNominal.setValue(String.valueOf(nominal.getValue()));
    }

    LiveData<Integer> getNominal() {
        return nominal;
    }

    public void setBank(Editable text) {
        banks.postValue(mainRepository.getBanks().getValue());
    }

    LiveData<Banks> getBank() {
        return banks;
    }
}
