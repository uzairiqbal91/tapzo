package com.example.mohsinhussain.allinoneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webview extends AppCompatActivity {

    private  static WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        browser = (WebView)findViewById(R.id.webView);


        String url=getIntent().getStringExtra("brandUrl");


        browser.getSettings().getJavaScriptEnabled();
        browser.loadUrl(url);
        // TextView textView= (TextView) findViewById(R.id.textview);
        browser.setWebChromeClient(new WebChromeClient());
        browser.setWebViewClient(new WebViewClient());

    }
}
