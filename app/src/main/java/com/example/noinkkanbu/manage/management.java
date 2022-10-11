package com.example.noinkkanbu.manage;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noinkkanbu.Adapter;
import com.example.noinkkanbu.R;

import com.example.noinkkanbu.model.Elder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class management extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton addman;
    ArrayList<Elder> elderList;
    FirebaseFirestore db;
    FirebaseUser user;

    private  FirestoreRecyclerAdapter adapter;
    View.OnClickListener cl;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_management,container,false);

        recyclerView = v.findViewById(R.id.kkanbulist);


        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        addman = v.findViewById(R.id.btn_addman);
        elderList = new ArrayList<Elder>();
        recyclerView.setAdapter(adapter);

        //Query
        Query query =  db.collection("Elders").whereEqualTo("managerToken",user.getUid());
        //RecyclerOptions

        Log.e("managerToken", user.getUid());
        FirestoreRecyclerOptions<Elder> options = new FirestoreRecyclerOptions.Builder<Elder>()
                .setQuery(query, Elder.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Elder, ElderViewHolder>(options) {


            @NonNull
            @Override
            public ElderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
                return new ElderViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ElderViewHolder holder, int position, @NonNull Elder model) {
                holder.manname.setText(model.getElderName());
                holder.elderph.setText(model.getElderPh());
                holder.mngph.setText(model.getMngPh());

            }
        };

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);



//        db.collection("Elders").whereEqualTo("managerToken",user.getUid()).addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
//                if (e != null) {
//                    Log.w(TAG, "Listen failed.", e);
//                    return;
//                }
//
//
//                for (QueryDocumentSnapshot doc : value) {
//                    if (doc.getData() != null) {
//                        Elder elder = doc.toObject(com.example.noinkkanbu.model.Elder.class);
//                        elderList.add(elder);
//                    }
//                    adapter.notifyDataSetChanged();
//                }
//                Log.d(TAG, "Current cites in CA: " + elderList);
//            }
//        });



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

        return v;
    }

    private class ElderViewHolder extends RecyclerView.ViewHolder {
        TextView manname, elderph, mngph;
        public ElderViewHolder(@NonNull View itemView) {
            super(itemView);

            manname = itemView.findViewById(R.id.name);
            elderph = itemView.findViewById(R.id.elder_ph);
            mngph = itemView.findViewById(R.id.mng_ph);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}