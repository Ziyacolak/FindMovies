package com.example.findmovies.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {WatchListEntity.class},version = 4,exportSchema = true)
public abstract class WatchListDatabase extends RoomDatabase {


    public abstract WatchListDAO watchListDAO ();


    private static WatchListDatabase INSTANCE;


    public static WatchListDatabase getWatchListDBInstance(Context context) {

        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WatchListDatabase.class,"DB NAME")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }





}
