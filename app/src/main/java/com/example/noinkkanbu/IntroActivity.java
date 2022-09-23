package com.example.noinkkanbu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noinkkanbu.utils.ProjectConstants;


public class IntroActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro); //xml , java 소스 연결





        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = getSharedPreferences(ProjectConstants.PREF_NAME,MODE_PRIVATE);
                myEdit = sharedPreferences.edit();
                if(sharedPreferences.getBoolean(ProjectConstants.IS_LOGIN, false) ){
                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(intent); //인트로 실행 후 바로 MainActivity로 넘어감.
                    finish();

                }
                else{
                    Intent intent = new Intent(getApplicationContext(), login.class);
                    startActivity(intent); //인트로 실행 후 바로 MainActivity로 넘어감.
                    finish();
                }

            }
        }, 1000); //1초 후 인트로 실행
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}