package com.example.findmovies.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.findmovies.CustomItemClickListener;
import com.example.findmovies.DetailPage;
import com.example.findmovies.MainActivity;
import com.example.findmovies.R;
import com.example.findmovies.SearchPage;
import com.example.findmovies.adapter.PostAdapter;
import com.example.findmovies.retrofit.ResultsFilm;
import com.example.findmovies.retrofit.Retro;
import com.example.findmovies.retrofit.Searching;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment implements CustomItemClickListener {


    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    Context context = getContext();
    PostAdapter adapter;
    List<Searching> searchingList = new ArrayList<>();

    SearchView searchView;

    ProgressBar progressBar;
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_search, container, false);





        recyclerView = rootView. findViewById(R.id.rcview);
        searchView = rootView.findViewById(R.id.srcview);
        progressBar = rootView.findViewById(R.id.progressBar);


        adapter = new PostAdapter(this,searchingList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));



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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

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

       return rootView;
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
                Toast.makeText(getActivity(), "Hata", Toast.LENGTH_SHORT).show();

            }
        });
    }



    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity().getApplication(), DetailPage.class);
        intent.putExtra("id",PostAdapter.movieList.get(position).getId());
        Log.d("ziya", String.valueOf(PostAdapter.movieList.get(position).getId()));
        intent.putExtra("movie",PostAdapter.movieList.get(position).getTitle());
        intent.putExtra("detail",PostAdapter.movieList.get(position).getOverview());
        intent.putExtra("rate",PostAdapter.movieList.get(position).getVote_average()/2);
        intent.putExtra("img",PostAdapter.movieList.get(position).getBackdrop_path());
        intent.putExtra("img2",PostAdapter.movieList.get(position).getPoster_path());
        startActivity(intent);


    }
}