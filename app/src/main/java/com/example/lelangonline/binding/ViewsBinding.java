package com.example.lelangonline.binding;

import android.graphics.Bitmap;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.RequestManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ViewsBinding {

    private static final String TAG = "ViewsBinding";


    @BindingAdapter({"loadImage", "reqManager"})
    public static void changeImage(ImageView imageView , String imageUrl,RequestManager requestManager){
        requestManager.load(imageUrl).into(imageView);
    }

    @BindingAdapter({"loadImageBarang", "reqManager"})
    public static void changeBarang(ImageView imageView , String imageUrl,RequestManager requestManager){
        String url = "http://10.0.3.2:8000/images/items/"+ imageUrl;
        requestManager.load(url).into(imageView);
    }


    @BindingAdapter("openWebView")
    public static void openArticle(WebView webView, String link){
        webView.loadUrl(link);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public Bitmap getDefaultVideoPoster() {
                return Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
            }
        });
    }

    @BindingAdapter({"authorName", "sourceName"})
    public static void setName(TextView textView, String authorName, String sourceName){
        if(authorName == null || authorName.length() < 2 || authorName.startsWith("<a")){
            textView.setText(sourceName);
        }else
            textView.setText(authorName);
    }

    @BindingAdapter({"categoryImage"})
    public static void setCategory(ImageView imageView, int drawable){
        imageView.setImageResource(drawable);
    }

    @BindingAdapter({"articleDescription", "articleTitle"})
    public static void setDesc(TextView textView, String desc, String title){
        if(desc != null && desc.length() > 2)
            textView.setText(desc + " ...More");
        else
            textView.setText(title+ " ...More");
    }

    @BindingAdapter("dateFormat")
    public static void getTodayDetailsDate(TextView textView, String date) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat output = new SimpleDateFormat("yyyy MM dd, hh:mm");
        Date d = null;
        try {
            d = input.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(d != null)
            textView.setText(output.format(d));
        else
            textView.setText(date);
    }

    @BindingAdapter("curencyFormat")
    public static void curencyFormat(TextView textView, int i){
        NumberFormat formatter = new DecimalFormat("#,###");
        textView.setText(String.format("Rp. %s", formatter.format(i)));
    }

}
