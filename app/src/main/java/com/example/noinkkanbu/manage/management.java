package com.example.noinkkanbu.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noinkkanbu.Adapter;
import com.example.noinkkanbu.R;
import com.example.noinkkanbu.pojo.Elder;
import com.example.noinkkanbu.retrofit.BaseEndPoint;
import com.example.noinkkanbu.retrofit.ElderEndPoint;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class management extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton addman;
    List<Elder> elderList;

    Adapter adapter;
    View.OnClickListener cl;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_management,container,false);

        recyclerView = view.findViewById(R.id.kkanbulist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        addman = view.findViewById(R.id.btn_addman);
        elderList = new ArrayList<>();



        ElderEndPoint elderEndPoint = BaseEndPoint.retrofit.create(ElderEndPoint.class);
        Elder elder = new Elder();
        Call<List<Elder>> call = elderEndPoint.getUserElders(1);
        call.enqueue(new Callback<List<Elder>>() {
            @Override
            public void onResponse(Call<List<Elder>> call, Response<List<Elder>> response) {


                elderList = (List<Elder>) response.body();
                adapter = new Adapter(getActivity(), elderList);

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Elder>> call, Throwable t) {

            }
        });


        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_addman:
                        Intent intent = new Intent(getContext(), AddOlderMan.class);
                        startActivity(intent);
                }

            }
        };

        addman.setOnClickListener(cl);

        return view;
    }

}