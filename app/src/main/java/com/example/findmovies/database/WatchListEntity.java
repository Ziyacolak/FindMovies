package com.example.findmovies.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WatchList")
public class WatchListEntity {


    @PrimaryKey(autoGenerate = false)
    public int key= 0;

    @ColumnInfo(name = "backdropPath")
    public String backdropPath;

    @ColumnInfo(name= "posterPath" )
    public String posterPath;

    @ColumnInfo(name = "movieId")
    public int movieId = 0;

    @ColumnInfo(name = "title")
    public String title;


    @ColumnInfo(name="overview")
    public String overview;

    @ColumnInfo(name="voteAverage")
    public Float voteAverage;


    public WatchListEntity(int key, String backdropPath, String posterPath, int movieId, String title, String overview, Float voteAverage) {
        this.key = key;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.movieId = movieId;
        this.title = title;
        this.overview = overview;
        this.voteAverage = voteAverage;
    }



}
