package com.example.findmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.findmovies.databinding.ActivityMainBinding;
import com.example.findmovies.ui.FavoriteFragment;
import com.example.findmovies.ui.SearchFragment;
import com.example.findmovies.ui.WatchlistFragment;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new SearchFragment());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {


            switch (item.getItemId()){
                case R.id.search:
                   replaceFragment(new SearchFragment());
                    break;

                case R.id.favorite:
                    replaceFragment(new FavoriteFragment());
                    break;
                case R.id.watchlist:
                    replaceFragment(new WatchlistFragment());
                    break;
            }
            return true;


        });


    }



    private void replaceFragment(Fragment fragment){


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }




}