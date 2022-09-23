package com.example.noinkkanbu;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class mqtttest extends AppCompatActivity {

    private String topic, clientID;
    //    private MqttAndroidClient client;
    private MyMqtt myMqtt;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtttest);
        button = findViewById(R.id.mqttsub);
        myMqtt = new MyMqtt();


    }





}