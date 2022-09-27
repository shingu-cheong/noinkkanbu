package com.example.noinkkanbu.manage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;


import com.example.noinkkanbu.R;
import com.example.noinkkanbu.SearchAddress;
import com.example.noinkkanbu.pojo.Elder;
import com.example.noinkkanbu.retrofit.BaseEndPoint;
import com.example.noinkkanbu.retrofit.ElderEndPoint;
import com.example.noinkkanbu.utils.ProjectConstants;
import com.google.android.material.textfield.TextInputLayout;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddOlderMan extends AppCompatActivity {
    Toolbar tb_addman;
    TextInputLayout et_manName, et_manPh, et_managerPh, et_detail;
    EditText  et_manAdr;
    SharedPreferences shared ;
    Button bt_addman;
    Integer uid ;
    View.OnClickListener cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_older_man);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        tb_addman = (Toolbar) findViewById(R.id.mt_toolbar);
        et_manName = findViewById(R.id.et_manName);
        et_manPh = findViewById(R.id.et_manPh);
        et_manAdr = findViewById(R.id.et_manAdress);
        et_manAdr.setFocusable(false);
        et_managerPh = findViewById(R.id.et_managerPh);
        et_detail = findViewById(R.id.et_manDetail);
        bt_addman = findViewById(R.id.addman);
        setSupportActionBar(tb_addman);


        shared= getSharedPreferences(ProjectConstants.PREF_NAME, MODE_PRIVATE);
        Log.e("User", String.valueOf(shared.getInt(ProjectConstants.USER_NUM,0)));
        uid = shared.getInt(ProjectConstants.USER_NUM,0);


        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.addman:
                        addman();
                    case R.id.et_manAdress:
                        Intent intent = new Intent(AddOlderMan.this, SearchAddress.class);
                        getSearchResult.launch(intent);

                }

            }
        };

        bt_addman.setOnClickListener(cl);
        et_manAdr.setOnClickListener(cl);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    public  void addman(){
        Elder elder = setElderData();
        ElderEndPoint elderEndPoint = BaseEndPoint.retrofit.create(ElderEndPoint.class);

        Call<String> addNewMan = elderEndPoint.saveElder(uid,elder);


        SweetAlertDialog pDialog = new SweetAlertDialog(AddOlderMan.this,SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading....");
        pDialog.setCancelable(true);
        pDialog.show();

        addNewMan.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                pDialog.dismiss();

                SweetAlertDialog message = new SweetAlertDialog(AddOlderMan.this, SweetAlertDialog.SUCCESS_TYPE);
                message.setTitleText(response.body()).show();
                message.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        finish();
                    }
                });

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



    }



    //데이터 삽입
    private Elder setElderData() {

        Elder elder = new Elder();

        elder.setElderAdr(et_manAdr.getText().toString());
        elder.setElderImg(null);
        elder.setElderName(et_manName.getEditText().getText().toString());
        elder.setElderPh(et_manPh.getEditText().getText().toString());
        elder.setMngPh(et_managerPh.getEditText().getText().toString());

        return elder;
    }

    private  final ActivityResultLauncher<Intent> getSearchResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        String data = result.getData().getStringExtra("data");
                        et_manAdr.setText(data);
                    }
                }
            }
    );

}