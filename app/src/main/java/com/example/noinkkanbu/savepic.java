package com.example.noinkkanbu;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.noinkkanbu.adapter.videoadpater;
import com.example.noinkkanbu.home.MainActivity2;
import com.example.noinkkanbu.manage.management;
import com.example.noinkkanbu.model.Elder;
import com.example.noinkkanbu.model.Video;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class savepic extends AppCompatActivity {
    FirebaseFirestore db;

    RecyclerView recyclerView;
    ArrayList<Video> list;
    videoadpater adapter;
    Video video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savepic);

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.videoRecycler);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Query query = db.collection("video").orderBy("createDate", Query.Direction.DESCENDING);

        adapter = new videoadpater(this, list);

        recyclerView.setAdapter(adapter);

        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "listen:error", e);
                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        Video video = dc.getDocument().toObject(Video.class);
                        list.add(video);

                    }
                }
                adapter.notifyDataSetChanged();


            }
        });

        // listAll(): 폴더 내의 모든 이미지를 가져오는 함수
//        listRef.listAll()
//                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
//                    @Override
//                    public void onSuccess(ListResult listResult) {
//                        int i = 1;
//                        // 폴더 내의 item이 동날 때까지 모두 가져온다.
//                        for (StorageReference item : listResult.getItems()) {
//
//                            // imageview와 textview를 생성할 레이아웃 id 받아오기
//                            LinearLayout layout = (LinearLayout) findViewById(R.id.maskImageLayout);
//                            // textview 동적생성
//                            TextView tv = new TextView(savepic.this);
//                            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                            tv.setText(i +"번째 사진");
//                            tv.setTextSize(25);
//                            tv.setTextColor(0xff004497);
//                            layout.addView(tv);
//                            i += 1;
//
//                            //imageview 동적생성
//                            ImageView iv = new ImageView(savepic.this);
//                            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                            layout.addView(iv);
//
//                            // reference의 item(이미지) url 받아오기
//                            item.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Uri> task) {
//                                    if (task.isSuccessful()) {
//                                        // Glide 이용하여 이미지뷰에 로딩
//                                        Glide.with(savepic.this)
//                                                .load(task.getResult())
//                                                .into(iv);
//                                    } else {
//                                        // URL을 가져오지 못하면 토스트 메세지
//                                        Toast.makeText(savepic.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    // Uh-oh, an error occurred!
//                                }
//                            });
//                        }
//                    }
//                });
    }



}

