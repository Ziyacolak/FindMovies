package com.example.findmovies.retrofit;

import android.graphics.drawable.Icon;

import com.google.gson.annotations.SerializedName;

public class Searching {

    //@SerializedName("movie")

   private String title;
   private String poster_path;
   private String backdrop_path;
   private int id;
   private String overview;
   private Float vote_average;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Float getVote_average() {
        return vote_average;
    }

    public void setVote_average(Float vote_average) {
        this.vote_average = vote_average;
    }

    public Searching(String title, String poster_path, String backdrop_path, int id, String overview, Float vote_average) {
        this.title = title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.overview = overview;
        this.vote_average = vote_average;
    }
}

