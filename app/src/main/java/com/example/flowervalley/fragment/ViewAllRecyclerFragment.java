package com.example.flowervalley.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flowervalley.R;
import com.example.flowervalley.adapter.ViewAllFlowerAdapter;
import com.example.flowervalley.model.FlowerRecyclerModal;

import java.util.ArrayList;


public class ViewAllRecyclerFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<FlowerRecyclerModal> arrFlower;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_all_recycler, container, false);

        recyclerView=view.findViewById(R.id.flower_recyclerview);
        recyclerView
                .setLayoutManager(new GridLayoutManager(getContext(),2));
        arrFlower=new ArrayList<>();
        arrFlower.add(new FlowerRecyclerModal(499,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2a3dgMOmuC-8OuMuBjoL9J6whE-QZ9ZK8rA&usqp=CAU","Angle"));
        arrFlower.add(new FlowerRecyclerModal(599,"https://bit.ly/3fLJf72","Rose"));
        arrFlower.add(new FlowerRecyclerModal(199,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2a3dgMOmuC-8OuMuBjoL9J6whE-QZ9ZK8rA&usqp=CAU","Pink"));
        arrFlower.add(new FlowerRecyclerModal(499,"https://bit.ly/3fLJf72","Rose"));
        arrFlower.add(new FlowerRecyclerModal(211,"https://bit.ly/3fLJf72","Rose"));
        arrFlower.add(new FlowerRecyclerModal(210,"https://bit.ly/3fLJf72","Sunflower"));
        arrFlower.add(new FlowerRecyclerModal(390,"https://bit.ly/3fLJf72","Angle"));

        recyclerView.setAdapter(new ViewAllFlowerAdapter(arrFlower,getContext()));

        return view;
    }
}