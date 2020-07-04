package com.example.allgodwallpaper;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;


import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ss.com.bannerslider.Slider;
import ss.com.bannerslider.event.OnSlideClickListener;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Slider slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.sub_drawer_layout);
        NavigationView navigationView = findViewById(R.id.sub_nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        String[] card_text = getResources().getStringArray(R.array.text_arr);
        List<String> new_txt = Arrays.asList(card_text);
        ArrayList<String> final_text = new ArrayList<>(new_txt);

        TypedArray card_img = getResources().obtainTypedArray(R.array.img_arr);
        ArrayList<Integer> final_img = new ArrayList<>();
        for (int i =0; i<card_img.length(); i++){
            final_img.add(card_img.getResourceId(i,0));
        }

        RecyclerView recyclerview = findViewById(R.id.recycle);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(final_img, final_text,getApplicationContext());
        recyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerview.setAdapter(adapter);

        Slider.init(new PicassoImageLoadingService(getApplicationContext()));
        slider = findViewById(R.id.banner_slider);
        slider.setAdapter(new BannerSliderAdapter());

        slider.setOnSlideClickListener(new OnSlideClickListener() {
            @Override
            public void onSlideClick(int position) {
                Intent intent = new Intent(getApplicationContext(),Videos.class);
                if(position == 0){
                    intent.putExtra("myurl","file:///android_asset/video.html");
                } if(position == 1){
                    intent.putExtra("myurl","file:///android_asset/video1.html");
                } if(position == 2){
                    intent.putExtra("myurl","file:///android_asset/video2.html");
                }
                startActivity(intent);
                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).cancel();
            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.sub_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.exit) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            return super.onOptionsItemSelected(item);
            }else if(item.getItemId() == R.id.share)
            {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }else if(item.getItemId() == R.id.rateus)
            {
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
                }
            }else if(item.getItemId()==R.id.privacy) {
            Intent intent=new Intent(getApplicationContext(),privacy_policy.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }else if(id==R.id.fav) {
            Intent intent = new Intent(getApplicationContext(), Fav.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
            return super.onOptionsItemSelected(item);
        }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id==R.id.home)
        {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.far) {
        Intent intent=new Intent(getApplicationContext(),Fav.class);
        startActivity(intent);
    }
       else if (id == R.id.shiv) {
            Intent intent=new Intent(getApplicationContext(),shivji_images.class);
            startActivity(intent);
        } else if (id == R.id.ganesh) {
            Intent intent=new Intent(getApplicationContext(),ganeshji_images.class);
            startActivity(intent);

        } else if (id == R.id.krishan) {
            Intent intent=new Intent(getApplicationContext(),krishanji_images.class);

            startActivity(intent);

        } else if (id == R.id.hanuman) {
            Intent intent=new Intent(getApplicationContext(),hanuman_images.class);

            startActivity(intent);

        } else if (id == R.id.ram) {
            Intent intent=new Intent(getApplicationContext(),shriramji_images.class);

            startActivity(intent);

        } else if (id == R.id.laxmi) {
            Intent intent=new Intent(getApplicationContext(),laxmiji_images.class);

            startActivity(intent);

        } else if (id == R.id.saraswati) {
            Intent intent=new Intent(getApplicationContext(),saraswati_images.class);

            startActivity(intent);

        } else if (id == R.id.vishnu) {
            Intent intent=new Intent(getApplicationContext(),vishnuji_images.class);

            startActivity(intent);

        } else if (id == R.id.sherovalimata) {
            Intent intent=new Intent(getApplicationContext(),sherovalimata_images.class);

            startActivity(intent);

        } else if (id == R.id.brahma) {
            Intent intent=new Intent(getApplicationContext(),brahmaji_images.class);

            startActivity(intent);

        }else if(id == R.id.nav_rate)
        {
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
            }
            catch (ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
            }
        }else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Here is the share content body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }else if(id==R.id.far){
            Intent intent=new Intent(getApplicationContext(),Fav.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.sub_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
