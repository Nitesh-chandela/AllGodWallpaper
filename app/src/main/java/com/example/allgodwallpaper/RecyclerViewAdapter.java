package com.example.allgodwallpaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    ArrayList<Integer> images;
    ArrayList<String> text;
    Context mcontext;

    public RecyclerViewAdapter(ArrayList<Integer> images, ArrayList<String> text, Context mcontext) {
        this.images = images;
        this.text = text;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.catlist, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.MyViewHolder holder, final int position) {
        holder.imageView.setImageResource(images.get(position));
        holder.textView.setText(text.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "position = " + holder.getLayoutPosition(), Toast.LENGTH_SHORT).cancel();
                if(holder.getLayoutPosition()==0)
                {
                    Intent intent=new Intent(mcontext,shivji_images.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);
                }else if(holder.getLayoutPosition()==1){
                    Intent intent=new Intent(mcontext,ganeshji_images.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }else if(holder.getLayoutPosition()==2){
                    Intent intent=new Intent(mcontext,krishanji_images.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }else if(holder.getLayoutPosition()==3){
                    Intent intent=new Intent(mcontext,hanuman_images.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }else if(holder.getLayoutPosition()==4){
                    Intent intent=new Intent(mcontext,shriramji_images.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }else if(holder.getLayoutPosition()==5){
                    Intent intent=new Intent(mcontext,laxmiji_images.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }else if(holder.getLayoutPosition()==6){
                    Intent intent=new Intent(mcontext,saraswati_images.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }else if(holder.getLayoutPosition()==7){
                    Intent intent=new Intent(mcontext,vishnuji_images.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }else if(holder.getLayoutPosition()==8){
                    Intent intent=new Intent(mcontext,sherovalimata_images.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }else if(holder.getLayoutPosition()==9){
                    Intent intent=new Intent(mcontext,brahmaji_images.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }
            }
        });
      Picasso.get().load(images.get(position)).fit().placeholder(R.drawable.lo).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.c1);
            textView = itemView.findViewById(R.id.t1);

        }
    }
}
