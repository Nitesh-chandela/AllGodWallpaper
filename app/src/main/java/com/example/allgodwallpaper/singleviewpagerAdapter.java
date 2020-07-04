package com.example.allgodwallpaper;


import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.internal.NavigationMenu;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import io.github.yavski.fabspeeddial.FabSpeedDial;



public class singleviewpagerAdapter extends PagerAdapter implements View.OnClickListener {
    int curruntPosition=0;
    private Context context;
    private ArrayList<Integer> image;
    singleviewpagerAdapter(Context context, ArrayList<Integer> image)
    {
     this.context=context;
     this.image=image;
    }
    @Override
    public int getCount() {

        return image.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.single_item_view_appbar, container, false);
        final ImageView imageView = view.findViewById(R.id.img);
        imageView.setImageResource(image.get(position));
        Picasso.get().load(image.get(position)).fit().placeholder(R.drawable.lo).into(imageView);
        ImageView wall = view.findViewById(R.id.wall);
        final ToggleButton toggleButton = view.findViewById(R.id.fav1);
        toggleButton.setChecked(false);
        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_favorite));


        FabSpeedDial fabSpeedDial = view.findViewById(R.id.git);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.home1:
                        Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                        context.startActivity(intent);
                        break;
                    case R.id.rate:
                        try {
                            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
                        } catch (ActivityNotFoundException e) {
                            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));
                        }
                        break;
                    case R.id.favf:
                        Intent in = new Intent(context.getApplicationContext(), Fav.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(in);
                        break;
                }

                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });


        final DBHelper db = new DBHelper(context, 0);
        final Cursor res = db.getalldataunique(image.get(position));
        if (res.getCount() > 0) {
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_favoritered));
            // favbtn.setText("UnFavoutite ");
        }
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Cursor res1 = db.getalldataunique(image.get(position));
                if (res1.getCount() > 0) {
                    db.delete(image.get(position));
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_favorite));

                    Toast.makeText(context, "Removed From Favourite", Toast.LENGTH_SHORT).show();
                    // favbtn.setText("Add Favorite");
                } else {
                    db.insertdata(image.get(position));
                    Toast.makeText(context, "Added To Favourite", Toast.LENGTH_SHORT).show();
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_favoritered));
                    //favbtn.setText("Remove Favorite");
                }
            }
        });


        wall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WallpaperManager myWallpaperManager = WallpaperManager.getInstance(context);
                try {
                    myWallpaperManager.setResource(image.get(position));
                    Toast.makeText(context, "Wallpaper Set!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(context, "Wallpaper error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      container.removeView((View) object);
    }

    @Override
    public void onClick(View view) {

    }
}

