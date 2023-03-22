package com.example.findmovies.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoritesDAO {


  @Query("SELECT * FROM favorites")
      List<FavoritesEntity> getAllFavorites();

  @Insert
    void insert(FavoritesEntity...favoritesEntities);

  @Query("DELETE FROM favorites WHERE `key` = :key")
    void delete(int key);

  @Query("SELECT EXISTS (SELECT 1 FROM favorites WHERE `key`= :key)")
  boolean checkisFavorites(int key) ;

 /* @Query("DELETE FROM favorites WHERE `key`")
    void deleteWithId(int key);*/







}
