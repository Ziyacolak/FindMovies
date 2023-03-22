package com.example.findmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.findmovies.CustomItemClickListener;
import com.example.findmovies.R;
import com.example.findmovies.database.WatchListEntity;
import com.example.findmovies.ui.WatchlistFragment;

import java.util.List;

public class WatchListAdapter extends RecyclerView.Adapter<WatchListAdapter.ViewHolder>{

    private final CustomItemClickListener listener;
    Context context;
    ImageView movieImg;
    List<WatchListEntity> watchListEntityList;

    public  WatchListAdapter(CustomItemClickListener listener, List<WatchListEntity> watchListEntityList) {
        this.watchListEntityList=watchListEntityList;
        this.listener=listener;
        this.context=context;
    }


    @NonNull
    @Override
    public WatchListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchListAdapter.ViewHolder holder, int position) {
        holder.movieTitle.setText(watchListEntityList.get(position).title);
        String url = "https://image.tmdb.org/t/p/w500";
        String fullUrl = url+watchListEntityList.get(position).posterPath;

       holder.movieTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(holder.getAdapterPosition());

            }
        });
        Glide.with(holder.itemView.getContext()).load(fullUrl).into(holder.movieImg);


    }

    @Override
    public int getItemCount() {
        return watchListEntityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView movieTitle;
        public ImageView movieImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movietit);
            movieImg = itemView.findViewById(R.id.imageV);
        }
    }
}
