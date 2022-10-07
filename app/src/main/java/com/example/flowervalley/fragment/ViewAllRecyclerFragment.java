package com.example.flowervalley.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.flowervalley.R;
import com.example.flowervalley.Utils;
import com.example.flowervalley.adapter.FlowerRecycleAdapter;
import com.example.flowervalley.adapter.ViewAllFlowerAdapter;
import com.example.flowervalley.model.FlowerRecyclerModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ViewAllRecyclerFragment extends Fragment {

    private FlowerRecyclerModal flower;
    private  RecyclerView recyclerView;
    private ArrayList<FlowerRecyclerModal> arrFlower;
    private DatabaseReference mDatabaseRef;
    AppCompatTextView back_icon;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_view_all_recycler, container, false);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("flowers");
        back_icon=view.findViewById(R.id.back_icon_view_all);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.replaceFragment(new HomeFragment(),getActivity());
            }
        });
        recyclerView=view.findViewById(R.id.flower_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        arrFlower=new ArrayList<>();
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrFlower.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    flower = postSnapshot.getValue(FlowerRecyclerModal.class);
                    Log.i(TAG, "onCreateView: Data > " + postSnapshot.getValue());
                    arrFlower.add(flower);                }
                recyclerView.setAdapter(new FlowerRecycleAdapter(getContext(), arrFlower));

            }
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(new ViewAllFlowerAdapter(arrFlower,getContext()));

        return view;
    }
}