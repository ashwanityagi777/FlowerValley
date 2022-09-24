package com.example.flowervalley;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Utils {

        public static void replaceFragment(Fragment fragment, FragmentActivity activity) {
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frame_Layout, fragment);
            ft.commit();
        }
}
