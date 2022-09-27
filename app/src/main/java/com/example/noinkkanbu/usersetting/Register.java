package com.example.noinkkanbu.usersetting;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.noinkkanbu.R;
import com.example.noinkkanbu.home.MainActivity2;
import com.example.noinkkanbu.pojo.User;
import com.example.noinkkanbu.retrofit.BaseEndPoint;
import com.example.noinkkanbu.retrofit.UserEndPoint;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    TextInputLayout  et_name, et_ph, et_psw2;
    TextView et_email;
    Button bt_join;
    FirebaseUser firebaseUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    View.OnClickListener cl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_email = findViewById(R.id.et_email);
        et_name = findViewById(R.id.et_name);
        et_ph = findViewById(R.id.et_ph);
        bt_join = findViewById(R.id.join_button);
        firebaseUser  = FirebaseAuth.getInstance().getCurrentUser();


        if (firebaseUser != null) {
            et_email.setText(firebaseUser.getEmail());
        }

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.join_button:
                        boolean validation = checkValidation();
                        if(validation){
                            User user = setUserData();
                            SweetAlertDialog pDialog = new SweetAlertDialog(Register.this,SweetAlertDialog.PROGRESS_TYPE);
                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            pDialog.setTitleText("Loading....");
                            pDialog.setCancelable(true);
                            pDialog.show();
                            Log.e("Fds",firebaseUser.getUid().toString());
                            db.collection("Users").document(firebaseUser.getUid())
                                    .set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            pDialog.hide();
                                            new SweetAlertDialog(Register.this)
                                                    .setTitleText("등록완료")
                                                    .show();
                                            Intent intent = new Intent(Register.this, MainActivity2.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            new SweetAlertDialog(Register.this, SweetAlertDialog.ERROR_TYPE)
                                                    .setTitleText("Oops..")
                                                    .setContentText(e.getMessage());
                                        }
                            });


//                            addNewUser.enqueue(new Callback<Map>() {
//                                @Override
//                                public void onResponse(Call<Map> call, Response<Map> response) {
//                                    pDialog.hide();
//                                    new SweetAlertDialog(RegisterActivity.this)
//                                            .setTitleText(response.body().get("message").toString())
//                                            .show();
//                                    Intent intent = new Intent(RegisterActivity.this, MainActivity2.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//
//                                @Override
//                                public void onFailure(Call<Map> call, Throwable t) {
//                                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
//                                            .setTitleText("Oops..")
//                                            .setContentText(t.getMessage())
//                                            .show();
//                                }
//                            });
//                            addNewUser.enqueue(new Callback<Map>() {
//                                @Override
//                                public void onResponse(Call<Map> call, Response<Map> response) {
//                                    pDialog.hide();
//                                    new SweetAlertDialog(RegisterActivity.this)
//                                            .setTitleText(response.body().get("message").toString())
//                                            .show();
//                                    Intent intent = new Intent(RegisterActivity.this, MainActivity2.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//
//                                @Override
//                                public void onFailure(Call<Map> call, Throwable t) {
//                                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
//                                            .setTitleText("Oops..")
//                                            .setContentText(t.getMessage())
//                                            .show();
//                                }
//                            });
                        }

                }

            }
        };

        bt_join.setOnClickListener(cl);

    }

//    private LoginRegistration setLoginRegistrationData() {
//        LoginRegistration loginRegistration = new LoginRegistration();
//        loginRegistration.setAddress1(null);
//        loginRegistration.setCity(null);
//        loginRegistration.setDob(null);
//        loginRegistration.setFullname(null);
//        loginRegistration.setGender("Male");
//        loginRegistration.setPincode(null);
//        loginRegistration.setState(null);
//
//        loginRegistration.setEmail(et_email.getEditText().getText().toString());
//        loginRegistration.setMobile(null);
//        loginRegistration.setPassword(et_psw.getEditText().getText().toString());
//
//        loginRegistration.setUsername(et_name.getEditText().getText().toString());
//        return loginRegistration;
//    }

    private User setUserData() {

        User user = new User();
//        user.setUserEmail(et_email.getEditText().getText().toString());
//        Log.e("a", et_email.getEditText().getText().toString());
        user.setUserName(et_name.getEditText().getText().toString());
//        user.setUserPassword(et_psw.getEditText().getText().toString());
        user.setUserImg(null);
        user.setUserPh(et_ph.getEditText().getText().toString());
        return user;
    }


    private boolean checkValidation() {
//        et_email = findViewById(R.id.et_email);
//        et_name = findViewById(R.id.et_name);
////        et_psw = findViewById(R.id.et_password);
//        et_psw2 = findViewById(R.id.et_passwordcheck);
        boolean validation = true;

//        if(et_email.getEditText().getText().toString().trim().length()<1){
//            et_email.getEditText().setError("이메일을 입력하지 않았습니다.");
//            validation = false;
//        }
        if(et_name.getEditText().getText().toString().trim().length()<1){
            et_name.getEditText().setError("닉네임을 입력하지 않았습니다");
            validation = false;
        }
//        if(et_psw.getEditText().getText().toString().trim().length()<1){
//            et_psw.getEditText().setError("비밀번호를 입력하지 않았습니다.");
//            validation = false;
//        }
        if(et_ph.getEditText().getText().toString().trim().length()<1){
            et_ph.getEditText().setError("핸드폰번호를 확인하지 않았습니다.");
            validation = false;
        }
//        else if(!et_psw2.getEditText().getText().toString().equals(et_psw.getEditText().getText().toString())){
//            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
//            validation = false;
//        }
        return validation;
    }
}