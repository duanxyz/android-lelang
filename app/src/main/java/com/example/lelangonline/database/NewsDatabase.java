package com.example.lelangonline.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lelangonline.database.saved.SavedDao;
import com.example.lelangonline.models.DataItem;
import com.example.lelangonline.models.saved.SavedBarang;

@Database(entities = {DataItem.class , com.example.lelangonline.models.auction.DataItem.class,
        SavedBarang.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();
    public abstract SavedDao savedDao();
}
