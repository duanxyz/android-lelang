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
    public static final String API_KEY = "";
    public static final String DATABASE_NAME = "user_database";
    public static final String ITEMS_TABLE_NAME = "items_table";
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
                dataItem.getPhoto(), dataItem.getInitialPrice(), dataItem.getStatus(), dataItem.getCreatedAt(), dataItem.getUpdatedAt());
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
        category.add(new Pair<>("World",R.drawable.ic_world));
        category.add(new Pair<>("Emas",R.drawable.ic_technology));
        category.add(new Pair<>("Business",R.drawable.ic_business));
        category.add(new Pair<>("Sports",R.drawable.ic_sport));
        category.add(new Pair<>("Entertainment",R.drawable.ic_entertainment));
        category.add(new Pair<>("Health", R.drawable.ic_health));
        category.add(new Pair<>("Science",R.drawable.ic_science));



        return category;
    }
//    public static List<Country> getCountries() {
//        List<Country> countryList = new ArrayList<>();
//        countryList.add(new Country("no", "Norway", "https://cdn.countryflags.com/thumbs/norway/flag-400.png"));
//        countryList.add(new Country("nl", "Netherlands", "https://cdn.countryflags.com/thumbs/netherlands/flag-400.png"));
//        countryList.add(new Country("ng", "Nigeria", "https://cdn.countryflags.com/thumbs/nigeria/flag-400.png"));
//        countryList.add(new Country("my", "Malaysia", "https://cdn.countryflags.com/thumbs/malaysia/flag-400.png"));
//        countryList.add(new Country("mx", "Mexico", "https://cdn.countryflags.com/thumbs/mexico/flag-400.png"));
//        countryList.add(new Country("ma", "Morocco", "https://cdn.countryflags.com/thumbs/morocco/flag-400.png"));
//        countryList.add(new Country("lv", "Latvia", "https://cdn.countryflags.com/thumbs/latvia/flag-400.png"));
//        countryList.add(new Country("sa", "Saudi Arabia", "https://cdn.countryflags.com/thumbs/saudi-arabia/flag-400.png"));
//        countryList.add(new Country("ru", "Russian Federation", "https://cdn.countryflags.com/thumbs/russia/flag-400.png"));
//        countryList.add(new Country("rs", "Serbia", "https://cdn.countryflags.com/thumbs/serbia/flag-400.png"));
//        countryList.add(new Country("ro", "Romania", "https://cdn.countryflags.com/thumbs/romania/flag-400.png"));
//        countryList.add(new Country("pt", "Portugal", "https://cdn.countryflags.com/thumbs/portugal/flag-400.png"));
//        countryList.add(new Country("pl", "Poland", "https://cdn.countryflags.com/thumbs/poland/flag-400.png"));
//        countryList.add(new Country("ph", "Philippines", "https://cdn.countryflags.com/thumbs/philippines/flag-400.png"));
//        countryList.add(new Country("nz", "New Zealand", "https://cdn.countryflags.com/thumbs/new-zealand/flag-400.png"));
//        countryList.add(new Country("lt", "Lithuania", "https://cdn.countryflags.com/thumbs/lithuania/flag-400.png"));
//        countryList.add(new Country("kr", "Korea, Republic of", "https://cdn.countryflags.com/thumbs/south-korea/flag-400.png"));
//        countryList.add(new Country("jp", "Japan", "https://cdn.countryflags.com/thumbs/japan/flag-400.png"));
//        countryList.add(new Country("it", "Italy", "https://cdn.countryflags.com/thumbs/italy/flag-400.png"));
//        countryList.add(new Country("in", "India", "https://cdn.countryflags.com/thumbs/india/flag-400.png"));
//        countryList.add(new Country("ie", "Ireland", "https://cdn.countryflags.com/thumbs/ireland/flag-400.png"));
//        countryList.add(new Country("hu", "Hungary", "https://cdn.countryflags.com/thumbs/hungary/flag-400.png"));
//        countryList.add(new Country("gr", "Greece", "https://cdn.countryflags.com/thumbs/greece/flag-400.png"));
//        countryList.add(new Country("gb", "United Kingdom", "https://cdn.countryflags.com/thumbs/united-kingdom/flag-400.png"));
//        countryList.add(new Country("fr", "France", "https://cdn.countryflags.com/thumbs/france/flag-400.png"));
//        countryList.add(new Country("eg", "Egypt", "https://cdn.countryflags.com/thumbs/egypt/flag-400.png"));
//        countryList.add(new Country("de", "Germany", "https://cdn.countryflags.com/thumbs/germany/flag-400.png"));
//        countryList.add(new Country("cz", "Czech", "https://cdn.countryflags.com/thumbs/czech-republic/flag-400.png"));
//        countryList.add(new Country("cu", "Cuba", "https://cdn.countryflags.com/thumbs/cuba/flag-400.png"));
//        countryList.add(new Country("co", "Colombia", "https://cdn.countryflags.com/thumbs/colombia/flag-400.png"));
//        countryList.add(new Country("cn", "China", "https://cdn.countryflags.com/thumbs/china/flag-400.png"));
//        countryList.add(new Country("ch", "Switzerland", "https://cdn.countryflags.com/thumbs/switzerland/flag-400.png"));
//        countryList.add(new Country("ca", "Canada", "https://cdn.countryflags.com/thumbs/canada/flag-400.png"));
//        countryList.add(new Country("br", "Brazil", "https://cdn.countryflags.com/thumbs/brazil/flag-400.png"));
//        countryList.add(new Country("bg", "Bulgaria", "https://cdn.countryflags.com/thumbs/bulgaria/flag-400.png"));
//        countryList.add(new Country("be", "Belgium", "https://cdn.countryflags.com/thumbs/belgium/flag-400.png"));
//        countryList.add(new Country("au", "Australia", "https://cdn.countryflags.com/thumbs/australia/flag-400.png"));
//        countryList.add(new Country("at", "Austria", "https://cdn.countryflags.com/thumbs/austria/flag-400.png"));
//        countryList.add(new Country("ar", "Argentina", "https://cdn.countryflags.com/thumbs/argentina/flag-400.png"));
//        countryList.add(new Country("za", "South Africa", "https://cdn.countryflags.com/thumbs/south-africa/flag-400.png"));
//        countryList.add(new Country("ve", "Venezuela", "https://cdn.countryflags.com/thumbs/venezuela/flag-400.png"));
//        countryList.add(new Country("us", "United States", "https://cdn.countryflags.com/thumbs/united-states-of-america/flag-400.png"));
//        countryList.add(new Country("ua", "Ukraine", "https://cdn.countryflags.com/thumbs/ukraine/flag-400.png"));
//        countryList.add(new Country("tw", "Taiwan", "https://cdn.countryflags.com/thumbs/taiwan/flag-400.png"));
//        countryList.add(new Country("tr", "Turkey", "https://cdn.countryflags.com/thumbs/turkey/flag-400.png"));
//        countryList.add(new Country("th", "Thailand", "https://cdn.countryflags.com/thumbs/thailand/flag-400.png"));
//        countryList.add(new Country("sk", "Slovakia", "https://cdn.countryflags.com/thumbs/slovakia/flag-400.png"));
//        countryList.add(new Country("si", "Slovenia", "https://cdn.countryflags.com/thumbs/slovenia/flag-400.png"));
//        countryList.add(new Country("sg", "Singapore", "https://cdn.countryflags.com/thumbs/singapore/flag-400.png"));
//        countryList.add(new Country("se", "Sweden", "https://cdn.countryflags.com/thumbs/sweden/flag-400.png"));
//        Collections.sort(countryList, (o1, o2) -> o1.getName().compareTo(o2.getName()));
//        return countryList;
//    }


}
