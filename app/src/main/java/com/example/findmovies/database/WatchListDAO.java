package com.example.findmovies.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WatchListDAO {

    @Insert
    void insert (WatchListEntity  watchListEntities);

   /* @Query("DELETE FROM watchlist WHERE `key`")
     void deleteWithId(int key );*/

    @Query("SELECT EXISTS (SELECT 1 FROM watchlist WHERE `key`= :key)")
    boolean checkisWatchlist(int key) ;

    @Query(("DELETE FROM watchlist WHERE `key` = :key"))
     void delete (int key);

    @Query("SELECT*FROM WatchList")
    List<WatchListEntity> getAllMovies();



}
