package com.angel266489.fortuneteller.database;

import android.arch.lifecycle.LiveData;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WishDAO {

    @Insert
    public void insert(Wish wish);

    @Query("DELETE FROM wish")
    public void deleteAll();

    @Query("select * from wish")
    public LiveData<List<Wish>> getAll();
}
