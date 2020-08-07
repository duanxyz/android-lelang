package com.example.lelangonline.ui.deposit.transfer;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.lelangonline.ui.MainRepository;
import com.example.lelangonline.utils.DataStatus;

import java.io.File;

import javax.inject.Inject;

public class TransferViewModel extends ViewModel {
    private MainRepository mainRepository;
    private MutableLiveData<Boolean> closeItem;
    private MutableLiveData<Boolean> openAtm;
    private MutableLiveData<Boolean> openInternet;
    private MutableLiveData<Boolean> openMobile;
    private MutableLiveData<Boolean> openUpload;
    private MutableLiveData<File> bitmap;
    private MutableLiveData<DataStatus> status;


    @Inject
    TransferViewModel(MainRepository mainRepository){
        this.mainRepository = mainRepository;
        closeItem = new MutableLiveData<>();
        openAtm = new MutableLiveData<>();
        openInternet = new MutableLiveData<>();
        openMobile = new MutableLiveData<>();
        openUpload = new MutableLiveData<>();
        bitmap = new MutableLiveData<File>();
        status = new MutableLiveData<>();
    }

    public String txt1(String s){
        return "Nomor Rekening "+s;
    }

    public String txt2(String s){
        return "Atas Nama: " + s;
    }

    public String atm(String s){
        return s + " ATM";
    }

    public String internet(String s){
        return s + " Internet Banking";
    }

    public String mobile(String s){
        return s + " Mobile Banking";
    }

    public void close(){
        closeItem.setValue(true);
    }

    public void setUpload(){
        openUpload.setValue(false);
    }

    public void setOpenUpload(){
        if (!openUpload.getValue()){
            openUpload.setValue(true);
        } else {
            openUpload.setValue(false);
        }
    }

    LiveData<Boolean> getOpenUpload(){ return openUpload; }

    LiveData<Boolean> getCloseItem() {
        return closeItem;
    }

    public String txtAtm(String bankName, String accountNumber){
        return "1. Masukan kartu debit " + bankName + " Anda ke dalam mesin ATM.\n\n" +
                "2. Setelah itu masukan PIN Anda untuk melanjutkan ke menu utama.\n\n" +
                "3. Pada menu utama cari pilihan TRANSAKSI LAINNYA. Di menu berikutnya tekan tulisan TRANSFER untuk melanjutkan.\n\n" +
                "4. Di menu TRANSFER, pilihan KE REKENING BANK " + bankName +" agar dapat menyelesaikan transaksi.\n\n" +
                "5. Pada menu ini, Anda akan diminta memasukkan nomor tujuan. Untuk hal ini, Anda cukup masukan "+ accountNumber + ".\n\n" +
                "6. Setelah itu lanjutkan ke menu berikutnya untuk memasukkan total saldo yang akan Anda top up.\n\n" +
                "7. Setelah itu, Anda cukup ikuti instruksi pada mesin ATM untuk menyelesaikan proses.";
    }

    public String txtInternet(String bankName, String accountNumber){
        return "1. Masuk pada website KLICK " + bankName + ".\n\n" +
                "2. Setelah masuk ke halaman depan, masukan ID dan Password Anda untuk login.\n\n" +
                "3. Pada menu utama, cari pilihan TRANSAKSI LAINNYA dan pada halaman yang muncul tekan TRANSFER.\n\n" +
                "4. Pada menu TRANSFER ini pilih KE REKENING BANK " + bankName + " untuk memulai transaksi.\n\n" +
                "5. Pada menu ini, pertama Anda ahrus masukan rekening tujuan. Untuk hal ini, Anda cukup masukan "+ accountNumber + ".\n\n" +
                "6. Setelah itu lanjut ke menu berikutnya untuk memasukan jumlah top up yang ingin dibayarkan.\n\n" +
                "7. Untuk menyelesaikan proses, ikuti instruksi yang diberikan pada menu KLICK " +bankName+ ".";
    }

    public String txtMobile(String bankName, String accountNumber){
        return "1. Buka Aplikasi M-" +bankName+ " Anda dan lakukan login.\n\n" +
                "2. Pada menu utama M-" +bankName+ ", pilih M-TRANSFER.\n\n" +
                "3. Pada menu ini, Anda cari pilihan BANK " +bankName+ ", untuk mencari pilihan kirim rekening tujuan.\n\n" +
                "4. Pada menu rekening tujuan ini, Anda cukup masukan "+ accountNumber + ".\n\n" +
                "5. Setelah itu, Anda lanjut ke menu berikutnya untuk memasukkan jumlah top up yang ingin Anda bayarkan.\n\n" +
                "6. Untuk menyelesaikan transaksi, ikuti arahan yang ada pada menu setelah memasukkan saldo.";
    }

    public void openAtm(){
        if (!openAtm.getValue()){
            openAtm.setValue(true);
        } else {
            openAtm.setValue(false);
        }
    }

    public void openInternet(){
        if (!openInternet.getValue()){
            openInternet.setValue(true);
        } else {
            openInternet.setValue(false);
        }
    }

    public void openMobile(){
        if (!openMobile.getValue()){
            openMobile.setValue(true);
        } else {
            openMobile.setValue(false);
        }
    }

    public void setOpenAtm(){
        openAtm.setValue(false);
    }

    public void setOpenInternet(){
        openInternet.setValue(false);
    }
    public void setOpenMobile(){
        openMobile.setValue(false);
    }

    LiveData<Boolean> getOpenAtm(){
        return openAtm;
    }

    LiveData<Boolean> getOpenInternet(){
        return openInternet;
    }
    LiveData<Boolean> getOpenMobile(){
        return openMobile;
    }

    public void setBitmap(File thumbnail) {
        bitmap.setValue(thumbnail);
    }

    public void postDeposit(){
        mainRepository.postDeposit(bitmap.getValue());
    }

    LiveData<DataStatus> getStatus(){
        return mainRepository.getStatus();
    }
}
