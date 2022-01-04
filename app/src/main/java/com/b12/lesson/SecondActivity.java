package com.b12.lesson;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    WebView webView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        webView = findViewById(R.id.webView);
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/*".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }
        }
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            setUrlToWebView(sharedText);
        }
    }

    private void setUrlToWebView(String url) {
        webView.loadUrl(url);
    }

}
