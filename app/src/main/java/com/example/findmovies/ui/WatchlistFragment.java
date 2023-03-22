package com.example.findmovies.ui;

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

import com.example.findmovies.CustomItemClickListener;
import com.example.findmovies.DetailPage;
import com.example.findmovies.R;
import com.example.findmovies.adapter.PostAdapter;
import com.example.findmovies.adapter.WatchListAdapter;
import com.example.findmovies.database.WatchListDatabase;
import com.example.findmovies.database.WatchListEntity;
import com.example.findmovies.databinding.RecyclerViewItemBinding;

import java.util.ArrayList;
import java.util.List;


public class WatchlistFragment extends Fragment implements CustomItemClickListener {


    RecyclerView watchListRcw;
    Context context = getContext();
    List<WatchListEntity> watchListEntityList = new ArrayList<>();
    WatchListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootViewW= inflater.inflate(R.layout.fragment_watchlist, container, false);

        WatchListDatabase database = WatchListDatabase.getWatchListDBInstance(getContext());
        watchListEntityList = database.watchListDAO().getAllMovies();
        adapter = new WatchListAdapter(this,watchListEntityList);
        watchListRcw = rootViewW.findViewById(R.id.watchl);
        watchListRcw.setAdapter(adapter);
        watchListRcw.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter.notifyDataSetChanged();


        return rootViewW;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity().getApplication(), DetailPage.class);
        intent.putExtra("id",watchListEntityList.get(position).movieId);
        Log.d("msg",watchListEntityList.get(position).title);
        intent.putExtra("movie",watchListEntityList.get(position).title);
        intent.putExtra("detail",watchListEntityList.get(position).overview);
        intent.putExtra("rate",watchListEntityList.get(position).voteAverage);
        intent.putExtra("img2",watchListEntityList.get(position).posterPath);
        intent.putExtra("img",watchListEntityList.get(position).backdropPath);
        startActivity(intent);

    }
}