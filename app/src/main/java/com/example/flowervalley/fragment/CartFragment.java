package com.example.flowervalley.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flowervalley.MainActivity;
import com.example.flowervalley.R;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flowervalley.MainActivity;
import com.example.flowervalley.R;
import com.example.flowervalley.SharedPreferenceManager;
import com.example.flowervalley.Utils;
import com.example.flowervalley.adapter.CartAdapter;
import com.example.flowervalley.model.CartModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    private static final String TAG = "CartFragment";
    private RecyclerView cartRecyclerview;
    private DatabaseReference mDatabaseRef;
    private FirebaseDatabase firebaseDatabase;
    private SharedPreferenceManager sharedPreferenceManager;
    private ArrayList<CartModel> cartModels;
    private AppCompatButton btnCheckOut;


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        btnCheckOut = view.findViewById(R.id.check_out);

        cartRecyclerview = view.findViewById(R.id.cart_recyclerview);

        cartModels = new ArrayList<>();
        sharedPreferenceManager = new SharedPreferenceManager(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("item_in_cart").child(sharedPreferenceManager.getPhone());

        cartRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        CartAdapter cartAdapter = new CartAdapter(cartModels, getContext());
        cartRecyclerview.setAdapter(cartAdapter);

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                cartModels.clear();
                CartModel cartModel = null;
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    cartModel = postSnapshot.getValue(CartModel.class);
                    Log.i(TAG, "cart Data > " + postSnapshot.getValue());
                    cartModels.add(cartModel);
                }
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.replaceFragment(new CheckOutFragment(), getActivity());
            }
        });

        return view;
    }
}