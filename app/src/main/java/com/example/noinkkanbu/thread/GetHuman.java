package com.example.noinkkanbu.thread;

import android.os.Build;
import android.util.Log;


import com.example.noinkkanbu.MyMqtt;

import org.json.JSONException;
import org.json.JSONObject;

public class GetHuman extends Thread {
    private MyMqtt myMqtt;
    Object SomethingResult;
    private String json;
    private JSONObject jsonObject =null , jsonpre = null;


    public GetHuman() {
        super();
    }

    @Override
    public void run() {
        myMqtt = new MyMqtt();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                myMqtt.subscribe("Sensor/Humancount");
                while (true){
                    jsonObject = myMqtt.getjsonobject();
                    if(jsonpre != jsonObject){
                        jsonpre = jsonObject;
                        if(jsonpre != null)
                        {
                            try {
                                SomethingResult = jsonpre.getInt("Human");
                                Log.e("sdfs", String.valueOf(SomethingResult));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }


                }
        }


    }
    public Object getSomethingResult(){
        return SomethingResult;
    }

    public void setSomethingResult(Object somethingResult) {
        SomethingResult = somethingResult;
    }
}

