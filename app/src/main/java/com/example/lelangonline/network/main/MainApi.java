package com.example.lelangonline.network.main;

import com.example.lelangonline.data.model.ResponseLogin;
import com.example.lelangonline.data.model.ResponseMember;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MainApi {

@FormUrlEncoded
@POST("auth/login")
Call<ResponseBody> loginRequest(@Field("email") String email,
                                  @Field("password") String password);
//@FormUrlEncoded
@GET("members/{id}")
Call<ResponseMember> memberRequest(@Path("id") int id);


//@POST("auth/login")
//Flowable<ResponseLogin> getLoginData(@Body LoggedInUser loggedInUser);

//    @GET("top-headlines")
//    Flowable<Response> getTopHead(
//            @Query("country") tring country,
//            @Query("page") int page,
//            @Query("pageSize") int size,
//            @Query("apiKey") String apiKey);
//
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
