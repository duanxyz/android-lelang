package com.example.lelangonline.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lelangonline.database.saved.SavedDao;

//@Database(entities = {ArticlesItem.class , SavedArticle.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();
    public abstract SavedDao savedDao();
}
