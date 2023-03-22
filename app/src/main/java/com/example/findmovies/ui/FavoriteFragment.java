package com.example.findmovies.ui;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.findmovies.R;
import com.example.findmovies.adapter.FavoritesAdapter;
import com.example.findmovies.adapter.PostAdapter;
import com.example.findmovies.database.FavoritesDatabase;
import com.example.findmovies.database.FavoritesEntity;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment implements CustomItemClickListener {



    RecyclerView favoriRcw;

    Context context = getContext();
    FavoritesAdapter adapter;
    TextView title;
    ImageView img;


    List<FavoritesEntity> favoritesEntitiesList ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootViewf= inflater.inflate(R.layout.fragment_favorite, container, false);



        favoriRcw = rootViewf.findViewById(R.id.favorircw);
        favoriRcw.setAdapter(adapter);
        favoriRcw.setHasFixedSize(true);
        favoriRcw.setLayoutManager(new LinearLayoutManager(getContext()));






        adapter = new FavoritesAdapter(this,favoritesEntitiesList);


        FavoritesDatabase database =FavoritesDatabase.getFavoritesDbInstance(getContext());
        database.favoritesDAO().getAllFavorites();
        Log.d("gara",database.favoritesDAO().getAllFavorites().toString());







      return rootViewf;
    }

    @Override
    public void onItemClick(int position) {

    }




}