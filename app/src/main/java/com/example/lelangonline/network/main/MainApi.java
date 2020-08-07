package com.example.lelangonline.network.main;

import com.example.lelangonline.data.model.ResponseLogin;
import com.example.lelangonline.data.model.ResponseMember;
import com.example.lelangonline.models.Response;
import com.example.lelangonline.models.auction.Auction;
import com.example.lelangonline.models.auction.DataItem;
import com.example.lelangonline.models.balance.ResponseBalance;
import com.example.lelangonline.models.banks.ResponseBanks;
import com.example.lelangonline.models.deposits.ResponseDeposit;
import com.example.lelangonline.models.users.Members;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApi {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                  @Field("password") String password);
    @POST("auth/login")
    Observable<ResponseLogin> login(@Field("email") String email,
                                    @Field("password") String password);

    @GET("members/{id}")
    Call<ResponseMember> memberRequest(@Path("id") int id);

    @GET("members")
    Flowable<Members> fetchMembersById(@Query("id") int id);

    @PUT("members/{id}")
    Flowable<Members> changeMembers(@Path("id") int id,
                                    @Body com.example.lelangonline.models.users.DataItem user);

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

    //balance
    @GET("history")
    Flowable<ResponseBalance> getBalance(@Query("id") String id,
                                         @Query("page") int page,
                                         @Query("limit") int size);

    //Banks
    @GET("banks")
    Flowable<ResponseBanks> getBanks();

    //deposit
    @Multipart
    @POST("deposits")
    Flowable<ResponseDeposit> postDeposit(@Query("wallet_id") String wallet_id,
                                          @Query("sent_date") String date,
                                          @Part MultipartBody.Part proof);
}
