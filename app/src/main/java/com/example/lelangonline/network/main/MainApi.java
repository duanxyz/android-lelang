package com.example.lelangonline.network.main;

import com.example.lelangonline.data.model.ResponseMember;
import com.example.lelangonline.models.Response;
import com.example.lelangonline.models.auction.Auction;
import com.example.lelangonline.models.auction.DataItem;
import com.example.lelangonline.models.users.Members;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
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
    @GET("members/{id}")
    Call<ResponseMember> memberRequest(@Path("id") int id);

    @GET("members")
    Flowable<Members> fetchMembersById(@Query("id") int id);

    //item
    @GET("items")
    Flowable<Response> getItem(@Query("page") int page,
                               @Query("limit") int size,
                               @Query("category") String category,
                               @Query("keyword") String keyword,
                               @Query("date") String date);

    //auction
    @POST("auctions")
    Observable<DataItem> createAuction(@Body DataItem auction);

    @GET("auctions")
    Flowable<Auction> getAuction(@Query("item_id") String item_id,
                                 @Query("page") int page,
                                 @Query("limit") int size);


}
