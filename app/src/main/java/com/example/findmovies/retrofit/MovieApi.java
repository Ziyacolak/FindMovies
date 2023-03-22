package com.example.findmovies.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("search/movie?api_key=16c40fcfdb09a118e31c619b5c28fb67")
    Call<ResultsFilm> getMovie(@Query("query")String query);

}
