package com.example.lelangonline.utils;

import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.util.Pair;

import com.example.lelangonline.R;
import com.example.lelangonline.models.DataItem;
import com.example.lelangonline.models.saved.SavedBarang;
//import com.example.lelangonline.models.ArticlesItem;
//import com.example.lelangonline.models.Country;
//import com.example.lelangonline.models.saved.SavedArticle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Constants {

    public static final String BASE_URL = "http://10.0.3.2:8000/api/";
    public static final String FIREBASE_URL = "http://10.0.3.2:8000/api/";
    public static final String API_KEY = "";
    public static final String DATABASE_NAME = "user_database";
    public static final String ITEMS_TABLE_NAME = "items_table";
    public static final String ITEMS_TABLE_NAME1 = "auctions_table";
    public static final String SAVED_TABLE_NAME = "saved_table";
    public static final String COUNTRY_PREFS = "countryPref";
    public static final String USERID_PREFS = "userIDPref";
    public static final String MEMBERID_PREFS = "memberIDPref";
    public static final String EMAIL_PREFS = "emailPref";
    public static final String USERNAME_PREFS = "usernamePref";
    public static final String SESSION_PREFS = "sessionPref";
    public static final String NAME_PREFS = "namePref";
    public static final String PHONE_PREFS = "phonePref";
    public static final String STATUS_PREFS = "statusPref";
    public static final String SALDO_PREFS = "saldoPref";
    public static final String AVATAR_PREFS = "avatarPref";
    public static final String COUNTRY_PREFS_NAME = "countryName";
    public static final String COUNTRY_PREFS_IMAGE = "countryImage";


    public static SavedBarang convertBarangClass(DataItem dataItem){
        return new SavedBarang(dataItem.getId(), dataItem.getCategory(), dataItem.getDeskripsi(), dataItem.getItemName(),
                dataItem.getPhoto(), dataItem.getInitialPrice(), dataItem.getStatus(), dataItem.getAuctionDate(), dataItem.getAuctionTime(), dataItem.getCreatedAt(), dataItem.getUpdatedAt());
    }

    public static String getTodayDate() {
        SimpleDateFormat day = new SimpleDateFormat("EEEE", Locale.getDefault());
        SimpleDateFormat month = new SimpleDateFormat("MMMM dd", Locale.getDefault());
        Date d = new Date();
        return day.format(d) + ", " + month.format(d);
    }



    public static boolean isConnected(ConnectivityManager connectivityManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (connectivityManager.getActiveNetwork() != null)
                return connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork())
                        .hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork())
                                .hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
            else
                return false;
        } else {
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        }
    }

    public static List<Pair<String, Integer>> getCategoryList(){
        List<Pair<String, Integer>> category = new ArrayList<>();
        category.add(new Pair<>("semua",R.drawable.ic_world));
        category.add(new Pair<>("emas",R.drawable.ic_technology));
        category.add(new Pair<>("kendaraan",R.drawable.ic_business));
        category.add(new Pair<>("pertanian",R.drawable.ic_sport));
        category.add(new Pair<>("peikanan",R.drawable.ic_entertainment));
        category.add(new Pair<>("sertifikat", R.drawable.ic_health));



        return category;
    }

}
