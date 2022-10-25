package com.example.noinkkanbu.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.noinkkanbu.R;
import com.example.noinkkanbu.model.Video;
import com.example.noinkkanbu.savepic;
import com.example.noinkkanbu.showvideo;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


public class videoadpater extends RecyclerView.Adapter <videoadpater.videoviewholder>{
    Context context;
    ArrayList<Video> list;

    public videoadpater(Context context, ArrayList<Video> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public videoadpater.videoviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.savevideo_item, parent, false);
        return  new videoviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull videoadpater.videoviewholder holder, int position) {
        Video video = list.get(position);
        holder.date.setText(video.getCreateDate().toDate().toString());
        Glide.with(context).load(video.getUrl()).into(holder.thumnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, showvideo.class);
                intent.putExtra("url", video.getUrl());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class videoviewholder extends RecyclerView.ViewHolder {
        TextView date;
        ImageView thumnail;
        public videoviewholder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.videodate);
            thumnail = itemView.findViewById(R.id.videoimage);



        }
    }
}
