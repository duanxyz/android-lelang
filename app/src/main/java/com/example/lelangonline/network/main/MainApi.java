package com.example.lelangonline.network.main;

import com.example.lelangonline.data.model.ResponseMember;
import com.example.lelangonline.models.Response;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApi {

    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                  @Field("password") String password);
    //@FormUrlEncoded
    @GET("members/{id}")
    Call<ResponseMember> memberRequest(@Path("id") int id);

    @GET("items")
    Flowable<Response> getTopHead(@Query("page") int page,
                                  @Query("limit") int size);
    @GET("items")
    Flowable<Response> getCategoryData(@Query("page") int page,
                                       @Query("limit") int size,
                                       @Query("category") String category);

//    @GET("top-headlines")
//    Flowable<Response> getCategoryData(
//            @Query("category") String query,
//            @Query("page") int page,
//            @Query("pageSize") int size,
//            @Query("apiKey") String apiKey);
//
//    @GET("everything")
//    Flowable<Response> getEverythingSearch(
//            @Query("q") String query,
//            @Query("page") int page,
//            @Query("pageSize") int size,
//            @Query("apiKey") String apiKey);


}
