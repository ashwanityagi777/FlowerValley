package com.example.flowervalley;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.flowervalley.fragment.CartFragment;
import com.example.flowervalley.fragment.FavoriteFragment;
import com.example.flowervalley.fragment.HomeFragment;
import com.example.flowervalley.fragment.LoginFragment;
import com.example.flowervalley.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
   public static BottomNavigationView bottomNavigationView;

Fragment fragment;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     bottomNavigationView =findViewById(R.id.bottom_navigation);
        replaceFragment(new LoginFragment());


        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        break;

                    case R.id.favorite:
                        replaceFragment(new FavoriteFragment());
                        break;
                    case R.id.cart:
                        replaceFragment(new CartFragment());
                        break;
                    case R.id.person:
                        replaceFragment(new ProfileFragment());
                        break;
                }

                return true;
            }
        });
    }
    void replaceFragment(Fragment fragment){
        MainActivity.bottomNavigationView.setVisibility(View.GONE);
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_Layout,fragment);
        fragmentTransaction.commit();

    }
}


