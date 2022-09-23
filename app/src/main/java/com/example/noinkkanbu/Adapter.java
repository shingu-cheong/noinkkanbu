package com.example.noinkkanbu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noinkkanbu.pojo.Elder;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.OldermanViewHolder> {
    Context context;
    List<Elder> elderArrayList;

    public Adapter(Context context, List<Elder> elderArrayList){
        this.context = context;
        this.elderArrayList = elderArrayList;
    }


    @NonNull
    @Override
    public OldermanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new OldermanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OldermanViewHolder oldermanViewHolder, int position) {

        oldermanViewHolder.manname.setText(elderArrayList.get(position).getElderName());
        oldermanViewHolder.elderph.setText(elderArrayList.get(position).getElderPh());
        oldermanViewHolder.mngph.setText(elderArrayList.get(position).getMngPh());
    }

    @Override
    public int getItemCount() {
        return elderArrayList.size();
    }

    public class OldermanViewHolder extends RecyclerView.ViewHolder {
        TextView manname, elderph, mngph;
        public OldermanViewHolder(@NonNull View itemView) {
            super(itemView);
            manname = itemView.findViewById(R.id.name);
            elderph = itemView.findViewById(R.id.elder_ph);
            mngph = itemView.findViewById(R.id.mng_ph);


        }
    }
}
