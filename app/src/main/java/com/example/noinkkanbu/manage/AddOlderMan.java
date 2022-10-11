package com.example.noinkkanbu.manage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;


import com.example.noinkkanbu.R;
import com.example.noinkkanbu.SearchAddress;
import com.example.noinkkanbu.model.Elder;
import com.example.noinkkanbu.retrofit.BaseEndPoint;
import com.example.noinkkanbu.retrofit.ElderEndPoint;
import com.example.noinkkanbu.usersetting.Register;
import com.example.noinkkanbu.utils.ProjectConstants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddOlderMan extends AppCompatActivity {
    Toolbar tb_addman;
    TextInputLayout et_manName, et_manPh, et_managerPh, et_detail;
    EditText  et_manAdr;
    SharedPreferences shared ;
    Button bt_addman, bt_button;
    String profilePath, uritxt, listsize;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;
    ImageView iv_elder;
    FirebaseStorage storage = FirebaseStorage.getInstance();;
    String elderId = UUID.randomUUID().toString();
    Uri galleryUri;
    byte[] data1;
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
        iv_elder = findViewById(R.id.elderImg);
        db = FirebaseFirestore.getInstance();
        firebaseUser  = FirebaseAuth.getInstance().getCurrentUser();
        setSupportActionBar(tb_addman);


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
                        break;
                    case R.id.et_manAdress:
                        Intent intent = new Intent(AddOlderMan.this, SearchAddress.class);
                        getSearchResult.launch(intent);
                        break;

                    case R.id.elderImg:
                        Intent galleryIntent = new Intent();
                        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                        galleryIntent.setType("image/*");
                        startActivityForResult(galleryIntent , 2);
                        break;
                }

            }
        };

        bt_addman.setOnClickListener(cl);
        et_manAdr.setOnClickListener(cl);
        iv_elder.setOnClickListener(cl);


    }


    public  void addman() {
        SweetAlertDialog pDialog = new SweetAlertDialog(AddOlderMan.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading....");
        pDialog.setCancelable(true);
        pDialog.show();
        Elder elder = setElderData();
//        ElderEndPoint elderEndPoint = BaseEndPoint.retrofit.create(ElderEndPoint.class);

//        Call<String> addNewMan = elderEndPoint.saveElder(uid,elder);
        db.collection("Elders").document(elderId).set(elder).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                imgupdate();
                pDialog.dismiss();
                new SweetAlertDialog(AddOlderMan.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("등록완료")
                        .show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pDialog.dismiss();
                new SweetAlertDialog(AddOlderMan.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("등록실패" + e.getMessage())
                        .show();
            }
        });
    }






//
//        addNewMan.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                pDialog.dismiss();
//
//                SweetAlertDialog message = new SweetAlertDialog(AddOlderMan.this, SweetAlertDialog.SUCCESS_TYPE);
//                message.setTitleText(response.body()).show();
//                message.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        finish();
//                    }
//                });
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
//            profilePath = data.getStringExtra("profilePath");
//            //파이어스토리지와 연결
//
//
//            Bitmap bitmap = (Bitmap) data.getParcelableExtra("data");
//            Log.e("dfs",bitmap.toString());
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            data1 = baos.toByteArray();
//
//            iv_elder.setImageBitmap(bitmap);
            Uri selectImageUri = data.getData();

            galleryUri = data.getData();
            iv_elder.setImageURI(galleryUri);




        }
    }


    private void imgupdate() {
//        profileImageView.setImageBitmap(bitmap);
        FirebaseStorage storage = FirebaseStorage.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //파이어스토어와 연결
        db = FirebaseFirestore.getInstance();
        StorageReference storageRef = storage.getReference();
//        Log.e("timestamp",Timestamp.n;

        Date date = Timestamp.now().toDate();
        StorageReference elderImagesRef = storageRef.child("ElderImg/"+elderId+"/file"+ date +".jpg");

        if(galleryUri != null){
            UploadTask uploadTask = elderImagesRef.putFile(galleryUri);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    elderImagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.e("이미지주소", uri.toString());
//                            storeuri = uri.toString();
                            db.collection("Elders").document(elderId)
                                    .update("elderImg", uri.toString());
                        }

                    });
                }
            });

        }


    }



    //데이터 삽입
    private Elder setElderData() {

        Elder elder = new Elder();
        elder.setElderAdr(et_manAdr.getText().toString());
        elder.setElderImg(null);
        elder.setElderName(et_manName.getEditText().getText().toString());
        elder.setElderPh(et_manPh.getEditText().getText().toString());
        elder.setMngPh(et_managerPh.getEditText().getText().toString());
        elder.setManagerToken(firebaseUser.getUid());
        return elder;
    }
//----------------------------주소 받아오기기
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



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }

}