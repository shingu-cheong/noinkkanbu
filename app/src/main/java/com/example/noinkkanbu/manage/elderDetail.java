package com.example.noinkkanbu.manage;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.noinkkanbu.R;
import com.example.noinkkanbu.model.Elder;

public class elderDetail extends AppCompatActivity {
    TextView dm_ph1, dm_name1, dm_edress1, dm_Uniq1, dm_bt1, dm_gd2, dm_gdname1, dm_gdph2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_management); //xml , java 소스 연결
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Elder elder = (Elder) getIntent().getSerializableExtra("model");
        Log.e("fsdgs", elder.getElderImg());

        dm_name1 = findViewById(R.id.dm_ph1);
        dm_ph1 = findViewById(R.id.dm_name1);
        dm_edress1 = findViewById(R.id.dm_edress1);
        dm_Uniq1 = findViewById(R.id.dm_Uniq1);
        dm_gd2 = findViewById(R.id.dm_gd2);
        dm_bt1 = findViewById(R.id.dm_bt1);
        dm_gdname1 = findViewById(R.id.dm_gdname1);
        dm_gdph2 = findViewById(R.id.dm_gdph2);

        dm_ph1.setText(elder.getElderPh());
        dm_name1.setText(elder.getElderName());
        dm_edress1.setText(elder.getElderAdr());
        


    }

}
