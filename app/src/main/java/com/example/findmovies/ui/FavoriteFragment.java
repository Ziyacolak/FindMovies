package com.example.findmovies.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findmovies.CustomItemClickListener;
import com.example.findmovies.DetailPage;
import com.example.findmovies.R;
import com.example.findmovies.adapter.FavoritesAdapter;
import com.example.findmovies.adapter.PostAdapter;
import com.example.findmovies.database.FavoritesDatabase;
import com.example.findmovies.database.FavoritesEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FavoriteFragment extends Fragment implements CustomItemClickListener {



    RecyclerView favoriRcw;

    Context context = getContext();
    FavoritesAdapter adapter;
    TextView title;
    ImageView img;


    List<FavoritesEntity> favoritesEntitiesList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootViewf= inflater.inflate(R.layout.fragment_favorite, container, false);

        FavoritesDatabase database =FavoritesDatabase.getFavoritesDbInstance(getContext());
        favoritesEntitiesList = database.favoritesDAO().getAllFavorites();
        adapter = new FavoritesAdapter(this,favoritesEntitiesList);

        favoriRcw = rootViewf.findViewById(R.id.favorircw);
        favoriRcw.setAdapter(adapter);
        favoriRcw.setHasFixedSize(true);
        favoriRcw.setLayoutManager(new LinearLayoutManager(getContext()));


        adapter.notifyDataSetChanged();
        Log.d("gara", String.valueOf(database.favoritesDAO().getAllFavorites().size()));
















      return rootViewf;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity().getApplication(), DetailPage.class);
        intent.putExtra("id",favoritesEntitiesList.get(position).movieId);
       Log.d("msg",favoritesEntitiesList.get(position).title);
        intent.putExtra("movie",favoritesEntitiesList.get(position).title);
        intent.putExtra("detail",favoritesEntitiesList.get(position).overview);
        intent.putExtra("rate",favoritesEntitiesList.get(position).voteAverage);
        intent.putExtra("img2",favoritesEntitiesList.get(position).posterPath);
        intent.putExtra("img",favoritesEntitiesList.get(position).backdropPath);
       startActivity(intent);



    }




}