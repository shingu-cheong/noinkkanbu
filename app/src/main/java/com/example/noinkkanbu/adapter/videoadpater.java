package com.example.noinkkanbu.adapter;

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
import com.example.noinkkanbu.showvideo;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.HashMap;


public class videoadpater extends FirestoreRecyclerAdapter<Video, videoadpater.videoviewholder>{


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public videoadpater(@NonNull FirestoreRecyclerOptions<Video> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull videoadpater.videoviewholder holder, int position, @NonNull Video model) {
        holder.date.setText(model.getCreateDate().toDate().toString());
        Glide.with(holder.image)
                .load(model.getUrl())
                .into(holder.image);

    }

    @NonNull
    @Override
    public videoadpater.videoviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.savevideo_item,parent, false);

        return new videoviewholder(view);
    }

    public class videoviewholder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView date;
        public videoviewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.videoimage);
            date = itemView.findViewById(R.id.videodate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(get, showvideo.class);
                }
            });
        }
    }



}
