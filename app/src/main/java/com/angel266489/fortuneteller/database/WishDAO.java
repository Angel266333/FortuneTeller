package com.angel266489.fortuneteller.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface WishDAO {

    @Insert
    public void insert(Wish wish);

    @Query("DELETE FROM wish")
    public void deleteAll();

    @Query("select * from wish")
    public List<Wish> getAll();
}
