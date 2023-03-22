package com.example.findmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.findmovies.adapter.PostAdapter;
import com.example.findmovies.retrofit.ResultsFilm;
import com.example.findmovies.retrofit.Retro;
import com.example.findmovies.retrofit.Searching;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPage extends AppCompatActivity implements CustomItemClickListener{
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    PostAdapter adapter;
    List<Searching> searchingList = new ArrayList<>();

    SearchView searchView;

    ProgressBar progressBar;
    Toolbar toolbar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        recyclerView = findViewById(R.id.rcview);
        searchView = findViewById(R.id.srcview);
        progressBar = findViewById(R.id.progressBar);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PostAdapter(this,searchingList);

        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(
                new RecyclerView.OnItemTouchListener() {
                    @Override
                    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                        return false;
                    }

                    @Override
                    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                        Log.d("ziya", rv + "");
                        Log.d("emre", rv.getAdapter().getItemCount() + "");
                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                }
        );




        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SearchPage.this,DetailPage.class);
                startActivity(i);

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                /*new Handler().postDelayed(() -> {
                    fetchPosts(query);
                }, 1000);*/
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    searchingList.clear();
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                } else {
                    new Handler().postDelayed(() -> {
                        fetchPosts(newText);
                    }, 1000);
                }
                return false;
            }
        });



    }

    private void fetchPosts(String query) {
        progressBar.setVisibility(View.VISIBLE);
        searchingList.clear();
        Retro.getClient().getMovie(query).enqueue(new Callback<ResultsFilm>() {
            @Override
            public void onResponse(Call<ResultsFilm> call, Response<ResultsFilm> response) {
                if (response.isSuccessful() && response.body() != null) {
                    {
                        progressBar.setVisibility(View.GONE);

                        searchingList.addAll(response.body().getResults());
                        adapter.notifyDataSetChanged();


                    }
                }
            }

            @Override
            public void onFailure(Call<ResultsFilm> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SearchPage.this, "Hata", Toast.LENGTH_SHORT).show();
            }
        });
    }




    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(SearchPage.this,DetailPage.class);
        intent.putExtra("movie", PostAdapter.movieList.get(position).getTitle());
        intent.putExtra("movie",PostAdapter.movieList.get(position).getBackdrop_path());
        intent.putExtra("movie",PostAdapter.movieList.get(position).getOverview());
        intent.putExtra("movie",PostAdapter.movieList.get(position).getVote_average());
        startActivity(intent);

    }
}

