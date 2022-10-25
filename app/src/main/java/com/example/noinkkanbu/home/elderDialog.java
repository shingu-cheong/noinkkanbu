package com.example.noinkkanbu.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noinkkanbu.R;
import com.example.noinkkanbu.manage.management;
import com.example.noinkkanbu.model.Elder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;

public class elderDialog extends DialogFragment {
    FirebaseFirestore db;
    FirebaseUser user;
    RecyclerView recyclerView;
    private FirestoreRecyclerAdapter adapter;

    public static final String TAG_EVENT_DIALOG = "dialog_event";

    public elderDialog(){}

    public static elderDialog getInstance(){
        elderDialog elderDialog = new elderDialog();
        return elderDialog;
    }

    //LetterWriteFragment에 데이터를 넘겨주기 위한 인터페이스
    public interface MyFragmentInterfacer {
        void onButtonClick(String input);
    }

    private MyFragmentInterfacer fragmentInterfacer;


    public void setFragmentInterfacer(MyFragmentInterfacer fragmentInterfacer){
        this.fragmentInterfacer = fragmentInterfacer;
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.elderlist_dialog, container);
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        recyclerView = v.findViewById(R.id.elder_name_recycler);

        TextView mSpinner = (TextView) v.findViewById(R.id.tv_elernameitem);
        Query query =  db.collection("Elders").whereEqualTo("managerToken",user.getUid());

        FirestoreRecyclerOptions<Elder> options = new FirestoreRecyclerOptions.Builder<Elder>()
                .setQuery(query, Elder.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Elder, ElderNameViewHolder>(options) {

            @NonNull
            @Override
            public ElderNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
                return new ElderNameViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ElderNameViewHolder holder, int position, @NonNull Elder model) {
                holder.bind(model);
            }
        };

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


//        //스피너 String-array와 연동
//        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_spinner_item,
//                getResources().getStringArray(R.array.member_call));
//        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinner.setAdapter(mAdapter);
//
//        Button mCancleBtn = (Button)v.findViewById(R.id.dialog_receiver_cancle_btn);
//
//        //확인버튼 누를시 스피너 값 LetterWriteFragment에 전달
//        Button mOkBtn = (Button)v.findViewById(R.id.dialog_receiver_ok_btn);
//        mOkBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Toast.makeText(getContext(), "test.", Toast.LENGTH_SHORT).show();
//                //선택한 스피너값 String으로 받기
//                String input = mSpinner.getSelectedItem().toString();
//                fragmentInterfacer.onButtonClick(input);
//                getDialog().dismiss();
//            }
//
//        });
//
//        mCancleBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getDialog().dismiss();
//            }
//        });

//        화면터치시 꺼짐 막기
        setCancelable(true);

        return v;
    }

    private class ElderNameViewHolder extends RecyclerView.ViewHolder {
        Elder eldermodel;
        TextView textView;
        public ElderNameViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_elernameitem);
        }

        public void bind(Elder model) {
            eldermodel = model;
            textView.setText(model.getElderName());

        }
    }
}
