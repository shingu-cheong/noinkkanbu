package com.example.noinkkanbu;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class cameratest extends AppCompatActivity {
    private WebView mWebVie; // 웹뷰 선언
    private WebSettings mWebSetting; //웹뷰세팅


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameratest);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mWebVie = (WebView) findViewById(R.id.webView);
        mWebVie.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        mWebSetting = mWebVie.getSettings(); //세부 세팅 등록
        mWebSetting.setJavaScriptEnabled(true); // 웹페이지 자바스클비트 허용 여부
        mWebSetting.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        mWebSetting.setJavaScriptCanOpenWindowsAutomatically(false); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSetting.setLoadWithOverviewMode(true); // 메타태그 허용 여부
        mWebSetting.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        mWebSetting.setSupportZoom(false); // 화면 줌 허용 여부
        mWebSetting.setBuiltInZoomControls(false); // 화면 확대 축소 허용 여부
        mWebSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        mWebSetting.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용 여부
        mWebSetting.setDomStorageEnabled(true); // 로컬저장소 허용 여부
        mWebVie.loadUrl("http://192.168.68:109:8090/?action=stream");

    }
}