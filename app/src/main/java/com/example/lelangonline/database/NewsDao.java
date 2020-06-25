package com.example.lelangonline.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.lelangonline.models.DataItem;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NewsDao {

    @Insert
    void insertBarang(List<DataItem> dataItemList);

    @Query("DELETE From items_table")
    void deleteBarang();

    @Query("SELECT * From items_table LIMIT :size OFFSET :offset ")
    Flowable<List<DataItem>> getBarang(int size, int offset);
}
