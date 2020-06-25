package com.example.lelangonline.database.saved;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lelangonline.models.saved.SavedBarang;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface SavedDao {

    @Insert
    void saveBarang(SavedBarang savedBarang);

    @Query("DELETE From saved_table WHERE id =:barangId")
    void deleteSavedBarang(String barangId);

    @Query("SELECT * From saved_table")
    Single<List<SavedBarang>> getSavedBarang();

    @Query("SELECT COUNT(*) From saved_table WHERE id =:name")
    Integer getSpecificSavedBarang(String name);

    @Query("SELECT COUNT(*) From saved_table WHERE id =:name")
    Single<Integer> singleSpecificSavedBarang(String name);
}
