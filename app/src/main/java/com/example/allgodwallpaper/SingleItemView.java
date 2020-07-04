package com.example.allgodwallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.viewpager.widget.ViewPager;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class SingleItemView extends AppCompatActivity {
    int myimage;
    String screen_type = "m";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myimage = getIntent().getIntExtra("myimage", 0);
        ArrayList<Integer> allImagesList = getIntent().getIntegerArrayListExtra("myImageList");

        int myImageIndex;
        myImageIndex = allImagesList.indexOf(myimage);
        setContentView(R.layout.activity_single_item_view);
        /*ImageView imageView=findViewById(R.id.single);
        imageView.setImageResource(myimage);*/

        screen_type = getIntent().getStringExtra("screen_type");
        Toolbar toolbar = findViewById(R.id.toolba);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ViewPager viewPager = findViewById(R.id.pager);
        singleviewpagerAdapter singleviewpagerAdapter = new singleviewpagerAdapter(this, allImagesList);
        viewPager.setAdapter(singleviewpagerAdapter);
        viewPager.setCurrentItem(myImageIndex);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (screen_type.contains("fav_screen")) {
            startActivity(new Intent(getApplicationContext(), Fav.class));
        } else {
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
        } else if (item.getItemId() == R.id.privacy) {
            Intent intent = new Intent(getApplicationContext(), privacy_policy.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (item.getItemId() == R.id.share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Here is the share content body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        } else if (item.getItemId() == R.id.rateus) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
            }
        } else if (id == R.id.fav) {
            Intent intent = new Intent(getApplicationContext(), Fav.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
}

