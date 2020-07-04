package com.example.allgodwallpaper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import java.util.ArrayList;

public class Fav extends AppCompatActivity {
ArrayList<Integer> list=new ArrayList<>();
    public static Activity selfintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        selfintent=this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DBHelper db=new DBHelper(getApplicationContext(),0);
        Cursor res=db.getalldata();
        if(res.getCount()>0){
            while(res.moveToNext()){
                int images=res.getInt(1);
                list.add(images);
            }

        }
        else{
            Toast.makeText(this, "No Favourite Images", Toast.LENGTH_SHORT).show();
        }
        RecyclerView recyclerview = findViewById(R.id.recycle4);
        shivjiAdapter adapter = new shivjiAdapter(list,getApplicationContext(),"fav_screen");
        recyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        recyclerview.setAdapter(adapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
        super.onBackPressed();
        }

    }

