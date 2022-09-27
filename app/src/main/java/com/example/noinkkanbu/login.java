package com.example.noinkkanbu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.example.noinkkanbu.home.MainActivity2;
import com.example.noinkkanbu.pojo.Login;
import com.example.noinkkanbu.pojo.User;
import com.example.noinkkanbu.retrofit.BaseEndPoint;
import com.example.noinkkanbu.retrofit.LoginEndPoint;
import com.example.noinkkanbu.retrofit.UserEndPoint;
import com.example.noinkkanbu.utils.ProjectConstants;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    
    EditText login_mail, login_psw;
    Button btn_login;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;

    View.OnClickListener cl;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_login);
        
        login_mail = (EditText)findViewById(R.id.login_email);
        login_psw = (EditText) findViewById(R.id.login_password);
        btn_login = (Button) findViewById(R.id.login_button);

        Button newpage = (Button)findViewById(R.id.join_button);
        newpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(login.this, RegisterActivity.class);
                startActivity(myintent);
                finish();
            }
        });
        
        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.login_button:
                        boolean validation = checkValidation();
                        if(validation){
                            Login login = new Login();

                            login.setUserEmail(login_mail.getText().toString());
                            Log.e("email", login_mail.getText().toString());
                            login.setUserPassword(login_psw.getText().toString());
                            LoginEndPoint loginEndPoint = BaseEndPoint.retrofit.create(LoginEndPoint.class);
                            UserEndPoint userEndPoint = BaseEndPoint.retrofit.create(UserEndPoint.class);



                            Call<User> checkMapCall = null;
                            try {
                                checkMapCall = loginEndPoint.checkUser(login);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            SweetAlertDialog pDialog = new SweetAlertDialog(login.this,SweetAlertDialog.PROGRESS_TYPE);
                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            pDialog.setTitleText("Loading....");
                            pDialog.setCancelable(false);
                            pDialog.show();
                            checkMapCall.enqueue(new Callback<User>() {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    if(response.code() == 200){
                                        pDialog.cancel();
//                                        new SweetAlertDialog(login.this)
//                                                .setTitleText("로그인 설")
//                                                .show();

                                        Log.e("usernum", response.body().toString());

                                        sharedPreferences = getSharedPreferences(ProjectConstants.PREF_NAME, MODE_PRIVATE);
                                        myEdit = sharedPreferences.edit();
                                        myEdit.putBoolean(ProjectConstants.IS_LOGIN, true);
                                        myEdit.putString(ProjectConstants.USER_EMAIL, login_mail.getText().toString());
                                        myEdit.putInt(ProjectConstants.USER_NUM, response.body().getId().intValue());
                                        myEdit.apply();


                                        Intent intent = new Intent(login.this, MainActivity2.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        pDialog.hide();
                                        new SweetAlertDialog(login.this, SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("이메일 또는 비밀번호가 틀렸습니다.")
                                                .show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    pDialog.hide();
                                    new SweetAlertDialog(login.this, SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText(t.toString())
                                            .show();
                                }
                            });


                        }
                        

                }
                
            }
        };
        
        btn_login.setOnClickListener(cl);
    }

    public void clicklogin(){

    }

    private boolean checkValidation() {
        boolean validation = true;
        if(login_mail.toString().trim().length()<1){
            Toast.makeText(this, "이메일 입력하세요", Toast.LENGTH_SHORT).show();
            validation = false;
        }
        if(login_psw.toString().trim().length()<1){
            Toast.makeText(this, "비밀번호 입력하세요", Toast.LENGTH_SHORT).show();
            validation = false;
        }
        return validation;
    }
}
