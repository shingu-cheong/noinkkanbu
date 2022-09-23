package com.example.noinkkanbu;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt3.Mqtt3Client;
import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish;
import com.hivemq.client.mqtt.mqtt3.message.subscribe.Mqtt3Subscribe;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MyMqtt {
    String s;
    JSONObject jsonObject;
    private Mqtt3Client client;

    public MyMqtt(){

        client= MqttClient.builder()
                .identifier(UUID.randomUUID().toString())
                .serverHost("152.67.192.198")
                .serverPort(1883)
                .useMqttVersion3()
                .buildAsync();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            client.toAsync().connect().whenComplete((mqtt3ConnAck, throwable) -> {
                if (throwable == null) {
                    Log.d("mqtt", "connect");
                } else {
                    Log.e("mqtterror", throwable.toString());
                }
            });
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void subscribe(String topic){
        Mqtt3Subscribe subscribeMessage = Mqtt3Subscribe.builder()
                .topicFilter(topic)
                .qos(MqttQos.EXACTLY_ONCE)
                .build();

//        client.toAsync().subscribe(subscribeMessage,System.out::println);
        client.toAsync().subscribeWith()
                .topicFilter(topic)
                .callback(publish -> {
                    byte[] payload = publish.getPayloadAsBytes();

                    s = new String(payload, StandardCharsets.UTF_8);
                    try {
                        jsonObject = new JSONObject(s);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("mqttpayload", String.valueOf(jsonObject));
                })
                .send()
                .whenComplete((subAck, throwable) -> {
                    if (throwable != null) {
                        // Handle failure to subscribe
                    } else {
                        Log.d("mqttcomplete",subAck.toString());
                    }
                });



    }
    public JSONObject getjsonobject(){

        return jsonObject;


    }

    public void publish(String a){
        Mqtt3Publish publish = Mqtt3Publish.builder()
                .topic(a)
                .qos(MqttQos.EXACTLY_ONCE)
                .payload(a.getBytes(StandardCharsets.UTF_8))
                .build();

        client.toAsync().publish(publish);
    }

    public Mqtt3Client getClient() {
        return client;
    }
}