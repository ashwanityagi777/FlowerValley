package com.example.flowervalley.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flowervalley.R;
import com.example.flowervalley.model.FlowerRecyclerModal;

import java.util.ArrayList;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;

import com.example.flowervalley.SharedPreferenceManager;
import com.example.flowervalley.Utils;
import com.example.flowervalley.fragment.FlowerDetailFragment;
import com.example.flowervalley.model.Flower;

public class ViewAllFlowerAdapter extends RecyclerView.Adapter<ViewAllFlowerAdapter.ViewHolder> {
        SharedPreferenceManager sharedPreferenceManager;
        ArrayList<FlowerRecyclerModal> list;
        Context context;
        int count = 1;

        public ViewAllFlowerAdapter(ArrayList<FlowerRecyclerModal> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flower_cart_recycler_view, parent, false);

            ViewHolder viewHolder=new ViewHolder(view);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            FlowerRecyclerModal flower = list.get(position);
            holder.flowerName.setText(""+flower.getFlowerName());
            holder.flowerPrice.setText(""+flower.getFlowerPrice());
            Glide.with(context)
                    .load(flower.getFlowerImageUrl())
                    .into(holder.flowerImage);

            sharedPreferenceManager = new SharedPreferenceManager(context);

            holder.addItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    sharedPreferenceManager.setItemCounter(count++);
                    Utils.addToCart(context,flower.getFlowerId(), flower.getFlowerName(), flower.getFlowerPrice(), flower.getFlowerImageUrl(), sharedPreferenceManager.getPhone());
                }
            });

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FlowerDetailFragment fragment = new FlowerDetailFragment();
                    Bundle bundle = new Bundle();

                    bundle.putString("flower_id", flower.getFlowerId());
                    bundle.putString("flower_name", flower.getFlowerName());
                    bundle.putInt("flower_price", flower.getFlowerPrice());
                    bundle.putString("flower_about", flower.getFlowerDescription());
                    bundle.putString("flower_image", flower.getFlowerImageUrl());
                    fragment.setArguments(bundle);
                    Utils.replaceFragment(fragment, (FragmentActivity) context);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            AppCompatTextView flowerName, flowerPrice;
            AppCompatImageView flowerImage;
            CardView cardView;
            AppCompatImageButton addItem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                cardView = itemView.findViewById(R.id.card);
                flowerName = itemView.findViewById(R.id.flower_name);
                flowerPrice = itemView.findViewById(R.id.flower_price);
                flowerImage = itemView.findViewById(R.id.flower_image);
                addItem = itemView.findViewById(R.id.btn_add_item);
            }
        }
    }