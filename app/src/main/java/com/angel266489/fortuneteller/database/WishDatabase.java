package com.angel266489.fortuneteller.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


@Database(entities = {Wish.class}, version = 2)
public abstract class WishDatabase extends RoomDatabase {

    private static WishDatabase INSTANCE;

    public abstract WishDAO wishDAO();

    // We get an only one instance of the database. This means that we can get the same DB instance every time.
    public static WishDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (WishDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WishDatabase.class, "wishDB")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}

