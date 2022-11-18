package com.example.noinkkanbu.home;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.noinkkanbu.R;
import com.example.noinkkanbu.graph;
import com.example.noinkkanbu.home.MainActivity;


import com.example.noinkkanbu.manage.management;

import com.example.noinkkanbu.setting;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavi);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new MainActivity()).commit();

        bottomNav.setSelectedItemId(R.id.home);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.home:
                        fragment = new MainActivity();
                        break;
                    case R.id.oldman:
                        fragment = new management();
                        break;
                    case R.id.setting:
                        fragment = new setting();
                        break;


                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                return true;
            }
        });

    }


}