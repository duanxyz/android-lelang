package com.example.lelangonline.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.lelangonline.models.DataItem;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBarang(List<DataItem> dataItemList);

    @Query("DELETE From items_table")
    void deleteBarang();

    @Query("SELECT * From items_table LIMIT :size OFFSET :offset ")
    Flowable<List<DataItem>> getBarang(int size, int offset);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAuction(List<com.example.lelangonline.models.auction.DataItem> dataItem);

    @Query("DELETE From auctions_table")
    void deleteAuction();

    @Query("SELECT * From auctions_table LIMIT :size OFFSET :offset ")
    Flowable<List<com.example.lelangonline.models.auction.DataItem>> getAuction(int size, int offset);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(List<com.example.lelangonline.models.users.DataItem> data);

    @Query("DELETE From user_table")
    void deleteUser();

    @Query("SELECT * From user_table WHERE id =:id")
    Flowable<List<com.example.lelangonline.models.users.DataItem>> getUser(int id);
}
