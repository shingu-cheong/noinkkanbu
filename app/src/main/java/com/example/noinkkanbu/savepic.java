package com.example.noinkkanbu;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class savepic extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savepic);

        // 이미지 폴더 경로 참조
        StorageReference listRef = FirebaseStorage.getInstance().getReference().child("photo");

        // listAll(): 폴더 내의 모든 이미지를 가져오는 함수
        listRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        int i = 1;
                        // 폴더 내의 item이 동날 때까지 모두 가져온다.
                        for (StorageReference item : listResult.getItems()) {

                            // imageview와 textview를 생성할 레이아웃 id 받아오기
                            LinearLayout layout = (LinearLayout) findViewById(R.id.maskImageLayout);
                            // textview 동적생성
                            TextView tv = new TextView(savepic.this);
                            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            tv.setText(Integer.toString(i)+". 번째 사진");
                            tv.setTextSize(25);
                            tv.setTextColor(0xff004497);
                            layout.addView(tv);

                            //imageview 동적생성
                            ImageView iv = new ImageView(savepic.this);
                            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            layout.addView(iv);

                            // reference의 item(이미지) url 받아오기
                            item.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        // Glide 이용하여 이미지뷰에 로딩
                                        Glide.with(savepic.this)
                                                .load(task.getResult())
                                                .into(iv);
                                    } else {
                                        // URL을 가져오지 못하면 토스트 메세지
                                        Toast.makeText(savepic.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Uh-oh, an error occurred!
                                }
                            });
                        }
                    }
                });
    }
}

