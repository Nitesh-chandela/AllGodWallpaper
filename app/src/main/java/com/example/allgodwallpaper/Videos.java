package com.example.allgodwallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import java.sql.Connection;

public class Videos extends AppCompatActivity {
    ProgressBar pb;
    String screen = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ConnectionDetector connectionDetector = new ConnectionDetector(getApplicationContext());
        if (connectionDetector.isConnected()) {



            pb = findViewById(R.id.progress1);
            WebView webView = (WebView) findViewById(R.id.video1);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new myweb());
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {

                    super.onPageStarted(view, url, favicon);
                    pb.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    pb.setVisibility(View.GONE);
                }
            });
            webView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        WebView webView = (WebView) v;

                        switch (keyCode) {
                            case KeyEvent.KEYCODE_BACK:
                                if (webView.canGoBack()) {
                                    webView.goBack();
                                    return true;
                                }
                                break;
                        }
                    }

                    return false;
                }
            });
            String url = getIntent().getStringExtra("myurl");
            webView.loadUrl(url);
        }
        else{
            Intent intent = new Intent(getApplicationContext(),notConnected.class);
            startActivity(intent);
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public class ConnectionDetector {
        Context context;

        public ConnectionDetector(Context context) {
            this.context = context;
        }

        public boolean isConnected(){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);

            if(cm != null){
                NetworkInfo info = cm.getActiveNetworkInfo();
                if (info != null){
                    if (info.getState() ==  NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }
            return false;
        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        super.onBackPressed();
    }


    class myweb extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            pb.setMax(100);
            pb.setProgress(newProgress);
            if (newProgress == 100) {
                pb.setVisibility(View.GONE);
            }
        }

    }
}
