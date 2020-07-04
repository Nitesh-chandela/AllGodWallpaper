package com.example.allgodwallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);
        getSupportActionBar().hide();
        splash sp=new splash();
         sp.start();

    }
public class splash extends Thread{

        public void run()
        {
            try{
                sleep(1200);
            }catch (InterruptedException e){
                e.getStackTrace();
            }
            Intent intent=new Intent(splashscreen.this,MainActivity.class);
            startActivity(intent);
            splashscreen.this.finish();
        }
}
}
