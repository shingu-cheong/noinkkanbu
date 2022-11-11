package com.example.noinkkanbu.utils;

import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noinkkanbu.R;

public class alram extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alram);

        // 버튼을 하나 만들어서 (레이아웃.xml파일에 생성해둔 버튼)
        Button btnVibrate = findViewById(R.id.btnVibrate);

        // 클릭하면 진동이 울리게 설정
        btnVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 1. Vibrator 객체 생성
                Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

                // 2. 진동 구현: 1000ms동안 100 강도의 진동
                vibrator.vibrate(VibrationEffect.createOneShot(1000,100));

            }
        });

    }
}
