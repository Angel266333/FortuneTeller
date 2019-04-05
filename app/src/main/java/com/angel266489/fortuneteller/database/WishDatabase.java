package com.angel266489.fortuneteller.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Wish.class}, version = 1)
public abstract class WishDatabase extends RoomDatabase {

    private static WishDatabase INSTANCE;

    // We get an only one instance of the database. This means that we can get the same DB instance every time.
    public static WishDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (WishDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WishDatabase.class, "wishDB")
                        .build();
            }
        }
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }

    //WishDatabase is creating abstract DAO object.
    abstract WishDAO wishDAO();

}
