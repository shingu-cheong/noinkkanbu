package com.example.noinkkanbu.manage;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.noinkkanbu.R;
import com.example.noinkkanbu.model.Elder;

public class elderDetail extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_management); //xml , java 소스 연결
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Elder elder = (Elder) getIntent().getSerializableExtra("model");
        Log.e("fsdgs", elder.getElderImg());

    }

}
