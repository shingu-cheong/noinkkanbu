package com.example.noinkkanbu.home;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;


import com.example.noinkkanbu.MyMqtt;
import com.example.noinkkanbu.R;
import com.example.noinkkanbu.home.monitoring;
import com.example.noinkkanbu.savepic;
import com.example.noinkkanbu.schedule;
import com.example.noinkkanbu.thread.GetHuman;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hivemq.client.internal.util.StringUtil;
import com.hivemq.client.mqtt.mqtt3.Mqtt3Client;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Fragment {
    private MyMqtt myMqtt;
    private ImageButton btn_live, btn_schedule, btn_videofile;
    private ImageView profile, present;
    private GetHuman getHuman;
    private int humancount, humancountpre;
    Object SomethingResult;
    private Mqtt3Client client;
    private String json, s;
    private JSONObject jsonObject =null , jsonpre = null;
    private String nowElderId;
    private List<String> namelist = new ArrayList<String>();
    private String[] items ;
    private View.OnClickListener cl;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        myMqtt = new MyMqtt();
        client= myMqtt.getClient();


//
        }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container,false);
        btn_live = view.findViewById(R.id.btn_live);
        btn_videofile = view.findViewById(R.id.btn_videofile);
        btn_schedule = view.findViewById(R.id.btn_schedule);
        profile = view.findViewById(R.id.home_profile);
        present = view.findViewById(R.id.present);
        GradientDrawable drawable = (GradientDrawable) getContext().getDrawable(R.drawable.home_profileround);
        profile.setBackground(drawable);
        profile.setClipToOutline(true);
        client.toAsync().subscribeWith().topicFilter("Sensor/Humancount").callback(subscribe ->{
            byte[] payload = subscribe.getPayloadAsBytes();

            s = new String(payload, StandardCharsets.UTF_8);
            try {
                jsonObject = new JSONObject(s);
                humancount = jsonObject.getInt("Human");
                if (humancount == 0){
                    present.setImageResource(android.R.drawable.presence_invisible);
                }else {
                    present.setImageResource(android.R.drawable.presence_online);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("mqttpayload", String.valueOf(jsonObject));
        }).send();


        Query query =  db.collection("Elders").whereEqualTo("managerToken",user.getUid());
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){
                        Log.e("무엇을 가져오나요", document.getString("elderName"));
                        namelist.add(document.getString("elderName"));
                        document.getId();

                    }

                }
            }
        });



        view.findViewById(R.id.btn_popup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

                builder
                    .setSingleChoiceItems(namelist.toArray(new String[0]), -1, new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int pos)
                        {
                            items =  namelist.toArray(new String[0]);
                            Toast.makeText(getActivity(),items[pos],Toast.LENGTH_LONG).show();
                        }
                    })
                    .setPositiveButton("선택", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
            }
        });



        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_live:
                        Intent a = new Intent(getActivity(), monitoring.class);
                        startActivity(a);
                        break;
                    case R.id.btn_schedule:
                        Intent b = new Intent(getActivity(), schedule.class);
                        startActivity(b);
                        break;
                    case R.id.btn_videofile:
                        Intent c = new Intent(getActivity(), savepic.class);
                        startActivity(c);
                        break;
                }
            }
        };
        btn_live.setOnClickListener(cl);
        btn_schedule.setOnClickListener(cl);
        btn_videofile.setOnClickListener(cl);
        return view;
    }
}
