package com.example.findmovies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Insert;

import com.bumptech.glide.Glide;
import com.example.findmovies.database.FavoritesDatabase;
import com.example.findmovies.database.FavoritesEntity;
import com.example.findmovies.database.WatchListDatabase;
import com.example.findmovies.database.WatchListEntity;
import com.example.findmovies.databinding.ActivityDetailPageBinding;
import com.example.findmovies.retrofit.Searching;
import com.example.findmovies.ui.FavoriteFragment;
import com.example.findmovies.ui.SearchFragment;
import com.example.findmovies.ui.WatchlistFragment;

public class DetailPage extends AppCompatActivity {

    private ImageView movieimg;
    private TextView movietitle;
    private TextView moviedetail;
    private RatingBar movierate;

    private FavoritesEntity favoritesEntity;
    private WatchListEntity watchListEntity;
    private FavoritesDatabase favoritesDatabase;
    private WatchListDatabase watchListDatabase;
    private Menu menu;
    private boolean favoritesB;
    private boolean watchlistB;
    private int key;
    private String title;
    private String detail;
    private Float rating;
    private String img;
    private String img2;


ActivityDetailPageBinding pageBinding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageBinding = ActivityDetailPageBinding.inflate(getLayoutInflater());
        setContentView(pageBinding.getRoot());



        watchListDatabase = WatchListDatabase.getWatchListDBInstance(this);
        favoritesDatabase = FavoritesDatabase.getFavoritesDbInstance(this);








        movieimg = findViewById(R.id.movieBack);
        moviedetail = findViewById(R.id.movieDetail);
        movietitle = findViewById(R.id.movieTitle);
        movierate = findViewById(R.id.movieRating);


        getDataFromIntent();






    }
    private void replaceFragment(Fragment fragment){


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
    private void getDataFromIntent() {


        if (getIntent().hasExtra("movie")){
            Log.d("ziya",getIntent().getExtras().getString("movie"));




         key=getIntent().getExtras().getInt("id");

         title= getIntent().getExtras().getString("movie");
         detail=getIntent().getExtras().getString("detail");
         rating=getIntent().getExtras().getFloat("rate");
         img2=getIntent().getExtras().getString("img2");

            movietitle.setText(title);
           moviedetail.setText(detail);
           movierate.setRating(rating);
           img=getIntent().getExtras().getString("img");


            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500"+
                            img)
                                    .into(movieimg);



        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        this.menu=menu;
        checkIsFavorites(key);
        checkIsWatchList(key);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.favorite1){
            if (favoritesB){
                menu.getItem(0).setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_favorite_border_24));
                favoritesB = false;
                deleteFavorites(key);
            } else {
                menu.getItem(0).setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_favorite_24));
                favoritesB = true;
                favoritesEntity=new FavoritesEntity(key,img,img2,key,title,detail,rating);
                insertFavorites(favoritesEntity);
            }
        }

        
        if (id==R.id.watchlist1){
            if (watchlistB){
                menu.getItem(1).setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_playlist_add_24));
                watchlistB= false;
                deleteWatchList(key);
            }else {
                menu.getItem(1).setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_format_list_bulleted_24));
                watchlistB = true;
                watchListEntity=new WatchListEntity(key,img,img2,key,title,detail,rating);
                insertWatchList(watchListEntity);

            }

        }
        return super.onOptionsItemSelected(item);
    }



    private void deleteFavorites(int key){
        favoritesDatabase.favoritesDAO().delete(key);
    }
    private void deleteWatchList(int key){
        watchListDatabase.watchListDAO().delete(key);
    }
    private void insertFavorites(FavoritesEntity favoritesEntity){
        favoritesDatabase.favoritesDAO().insert(favoritesEntity);
    }
    private void insertWatchList(WatchListEntity watchListEntity){
        watchListDatabase.watchListDAO().insert(watchListEntity);
    }
    private void checkIsWatchList( int key){
        boolean exist= watchListDatabase.watchListDAO().checkisWatchlist(key);
        if (exist){
            menu.getItem(1).setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_format_list_bulleted_24));
            watchlistB = true;

        }else { menu.getItem(1).setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_playlist_add_24));

           watchlistB = false;}

    }

    private void checkIsFavorites( int key) {
        boolean exist = favoritesDatabase.favoritesDAO().checkisFavorites(key);
        if (exist) {
            menu.getItem(1).setIcon(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_24));
            favoritesB = true;

        } else {
            menu.getItem(1).setIcon(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_border_24));

            favoritesB = false;
        }

    }}
