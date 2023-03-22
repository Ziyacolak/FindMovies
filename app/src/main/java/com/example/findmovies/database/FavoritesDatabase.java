package com.example.findmovies.database;


import android.content.Context;

import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.findmovies.ui.FavoriteFragment;

@Database(entities = {FavoritesEntity.class},version = 3,exportSchema = true )
public abstract class FavoritesDatabase extends RoomDatabase {




    public abstract FavoritesDAO favoritesDAO ();


    private static FavoritesDatabase INSTANCE;


    public static FavoritesDatabase getFavoritesDbInstance(Context context){

        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),FavoritesDatabase.class,"DB NAME")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }



}
