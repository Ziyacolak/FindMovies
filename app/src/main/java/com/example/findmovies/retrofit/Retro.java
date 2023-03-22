package com.example.findmovies.retrofit;

import com.example.findmovies.Base_Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {
    private static Retrofit retrofit =null;
     public static MovieApi getClient(){
         if (retrofit==null) {
         retrofit = new Retrofit.Builder()
                 .baseUrl(Base_Url.BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
         }
         return retrofit.create(MovieApi.class);
         }
     }

