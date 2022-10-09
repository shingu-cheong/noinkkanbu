package com.example.noinkkanbu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.noinkkanbu.home.MainActivity;
import com.example.noinkkanbu.usersetting.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class setting extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting, container, false);

        Button logoutButton = (Button) view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
                Toast.makeText(getContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);

                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
            }

        });

        return view;
    }

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
    }
}
