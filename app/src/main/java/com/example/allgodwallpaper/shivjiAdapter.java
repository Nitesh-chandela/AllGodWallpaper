package com.example.allgodwallpaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class shivjiAdapter extends RecyclerView.Adapter<shivjiAdapter.shivjiHolder>{
    ArrayList<Integer> image;
    Context mcontext;
    String screen_type;

    public shivjiAdapter(ArrayList<Integer> image, Context mcontext,String screen_type) {
        this.image = image;
        this.mcontext = mcontext;
        this.screen_type = screen_type;
    }


    @Override
    public shivjiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.sublist,parent,false);
        return new shivjiHolder(view);
    }

    @Override
    public void onBindViewHolder(shivjiHolder holder, final int position) {
        //holder.img.setImageResource(image.get(position));
        Picasso.get().load(image.get(position)).fit().placeholder(R.drawable.lo).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mcontext,SingleItemView.class);
                intent.putExtra("myimage",image.get(position));
                intent.putExtra("screen_type",screen_type);
                intent.putIntegerArrayListExtra("myImageList",image);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return image.size();
    }

    public class shivjiHolder extends RecyclerView.ViewHolder{
        ImageView img;


        public shivjiHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.subc1);

        }
    }
}
