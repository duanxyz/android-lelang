package com.example.lelangonline.ui.home;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

public class HomeAdapter  {

    private RequestManager requestManager;
    private RequestOptions requestOptions;
    private HomeViewModel homeViewModel;

    public HomeAdapter(RequestManager requestManager, RequestOptions requestOptions) {
        this.requestManager = requestManager;
        this.requestOptions = requestOptions;
    }
}
