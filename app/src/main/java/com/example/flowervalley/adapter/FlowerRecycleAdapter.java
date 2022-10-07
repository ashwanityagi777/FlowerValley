package com.example.flowervalley.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flowervalley.BottomMenuHelper;
import com.example.flowervalley.FlowerDetailFragment;
import com.example.flowervalley.MainActivity;
import com.example.flowervalley.R;
import com.example.flowervalley.SharedPreferenceManager;
import com.example.flowervalley.Utils;
import com.example.flowervalley.model.FlowerRecyclerModal;

import java.util.ArrayList;


public class FlowerRecycleAdapter extends RecyclerView.Adapter<FlowerRecycleAdapter.ViewHolder> {
        SharedPreferenceManager sharedPreferenceManager;

        ArrayList<FlowerRecyclerModal> arrFlower;
        Context context;
        int count = 1;
        private String flower_name,flower_price,flower_about;


        public FlowerRecycleAdapter(Context context, ArrayList<FlowerRecyclerModal> arrFlower)
        {
            this.arrFlower=arrFlower;
            this.context=context;

        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.flower_cart_recycler_view,parent,false);
            ViewHolder viewHolder=new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            FlowerRecyclerModal flowerRecyclerModel=arrFlower.get(position);


            holder.flower_name.setText(flowerRecyclerModel.getFlowerName());
            holder.flower_price.setText(flowerRecyclerModel.getFlowerPrice());

            Glide.with(context)
                    .load(flowerRecyclerModel.getFlowerImageUrl())
                    .into(holder.flower_img);


            sharedPreferenceManager = new SharedPreferenceManager(context);

            holder.addItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    sharedPreferenceManager.setItemCounter(count++);
                    BottomMenuHelper.showBadge(context, MainActivity.bottomNavigationView, R.id.cart, "" + sharedPreferenceManager.getItemCounter());


                }
            });

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FlowerDetailFragment fragment = new FlowerDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("flower_id", flowerRecyclerModel.getFlowerId());
                    bundle.putString("flower_name",flowerRecyclerModel.getFlowerName());
                    bundle.putString("flower_price",flowerRecyclerModel.getFlowerPrice());
                    bundle.putString("flower_about",flowerRecyclerModel.getFlowerDescription());
                    bundle.putString("flower_image",flowerRecyclerModel.getFlowerImageUrl());
                    fragment.setArguments(bundle);
                    Utils.replaceFragment(fragment, (FragmentActivity) context);
                }
            });

        }

        @Override
        public int getItemCount() {



            return arrFlower.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            AppCompatImageView flower_img;
            AppCompatTextView flower_name,flower_price;
            CardView cardView;
            AppCompatImageButton addItem;



            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                flower_img=itemView.findViewById(R.id.flower_image);
                flower_name =itemView.findViewById(R.id.flower_name);
                flower_price =itemView.findViewById(R.id.flower_price);
                cardView = itemView.findViewById(R.id.card);
                addItem = itemView.findViewById(R.id.btn_add_item);

            }
        }
    }

