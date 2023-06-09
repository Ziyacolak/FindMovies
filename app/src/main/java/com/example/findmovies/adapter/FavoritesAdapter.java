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
import com.example.findmovies.database.FavoritesEntity;
import com.example.findmovies.ui.FavoriteFragment;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder>{



    Context context;

    ImageView movieImg;
    CustomItemClickListener listener;

      List<FavoritesEntity> favoritesEntityList = new ArrayList<>();

    public FavoritesAdapter(CustomItemClickListener listener, List<FavoritesEntity> favoritesEntitiesList) {

        this.favoritesEntityList=favoritesEntitiesList;
        this.listener=listener;
        this.context= context;


    }

    @NonNull
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.ViewHolder holder, int position) {

        holder.movieTitle.setText(favoritesEntityList.get(position).title);
        String url = "https://image.tmdb.org/t/p/w500";
        String fullUrl = url+favoritesEntityList.get(position).posterPath;

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
        return favoritesEntityList.size();

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


   /* public static FavoritesEntity getselected(int position){
        if (favoritesEntityList !=null){
            if (favoritesEntityList.size()>0){
                return favoritesEntityList.get(position);
            }
        }
        return null;
    }*/

}
