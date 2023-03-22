package com.example.findmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.findmovies.CustomItemClickListener;
import com.example.findmovies.DetailPage;
import com.example.findmovies.R;
import com.example.findmovies.SearchPage;
import com.example.findmovies.database.FavoritesEntity;
import com.example.findmovies.retrofit.ResultsFilm;
import com.example.findmovies.retrofit.Searching;
import com.example.findmovies.ui.FavoriteFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter <PostAdapter.ViewHolder> {


    public static List<Searching> movieList;
    private static int position;


    private CustomItemClickListener listener;
    private Context context;








    public PostAdapter(CustomItemClickListener listener,List<Searching>movieList) {
        this.movieList = movieList;
        this.listener = listener;
        this.context= context;
    }




    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int position ) {
        holder.tvName.setText(movieList.get(position).getTitle());
        String url = "https://image.tmdb.org/t/p/w500";
        String fullUrl = url + movieList.get(position).getPoster_path();
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                listener.onItemClick(holder.getAdapterPosition());



            }
        });

        Glide.with(holder.itemView.getContext()).load(fullUrl).into(holder.ivLogo);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        ImageView ivLogo;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            tvName = itemView.findViewById(R.id.title);
            ivLogo = itemView.findViewById(R.id.image);




        }
    }

    public static Searching getSelectedMovie(int position){
        if (movieList !=null){
            if (movieList.size() > 0){
                return movieList.get(position);
            }
        }
        return null;
    }
}

