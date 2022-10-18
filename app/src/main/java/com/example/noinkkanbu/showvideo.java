package com.example.noinkkanbu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

public class showvideo extends AppCompatActivity {
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showvideo);

        videoView = findViewById(R.id.videoView);

        // 이미지 폴더 경로 참조

        StorageReference listRef = FirebaseStorage.getInstance().getReference().child("fall2.mp4");


        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/noinkkanbu.appspot.com/o/video%2F2022-10-18%2019_07_22.mp4?alt=media&token=5b24a7e3-0a03-485c-9181-2708163d8fc9");
        Log.e("fsdf",uri.toString());
        videoView.setVideoURI(uri);
        videoView.setMediaController(new MediaController(this));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        //비디오 일시 정지
        if(videoView!=null && videoView.isPlaying()) videoView.pause();
    }
    //액티비티가 메모리에서 사라질때..
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //
        if(videoView!=null) videoView.stopPlayback();
    }

}