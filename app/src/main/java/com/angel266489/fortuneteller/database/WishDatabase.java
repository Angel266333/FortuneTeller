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


@Database(entities = {Wish.class}, version = 1)
public abstract class WishDatabase extends RoomDatabase {

    private static WishDatabase INSTANCE;

    public abstract WishDAO wishDAO();

    // We get an only one instance of the database. This means that we can get the same DB instance every time.
    public static WishDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (WishDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WishDatabase.class, "wishDB")
                        .addCallback(new RoomDatabase.Callback() {
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                new Populate(INSTANCE).execute();
                            }
                        })
                        .build();
            }
        }
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }

    // WishDatabase is creating abstract DAO object.
    // The Populate class extends AsyncTask to call method doInBackground().
    static class Populate extends AsyncTask<Void, Void, Void> {
        private WishDatabase db;

        public Populate(WishDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            WishDAO dao = db.wishDAO();
            dao.deleteAll();
            ArrayList<Wish> wishList = new ArrayList<>();
            Wish firstWish = new Wish("Good fortune will strike you on the way.", "angelilianov@gmail.com", true);
            Wish secondWish = new Wish("Good fortune will strike you on the way.", "angelilianov@gmail.com", true);
            wishList.add(firstWish);
            wishList.add(secondWish);
            for (Wish w : wishList) {
                dao.insert(w);
            }
            List<Wish> anotherList = dao.getAll();
            Log.d("db", "Inserted in DB" + anotherList);
            return null;
        }
    }
}

