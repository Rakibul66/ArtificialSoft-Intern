package com.dailycalorie.cast.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.dailycalorie.cast.Fragments.FragmentWeather;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.dailycalorie.cast.Fragments.FragmentHome;
import com.dailycalorie.cast.R;

public class MainActivity extends AppCompatActivity {

    ImageView settings;
    ChipNavigationBar bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setItemSelected(R.id.nav_home, true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FragmentHome()).commit();
        }
//botom navigation iteam click event
        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment selectedFragment = null;
                switch (i) {
                    case R.id.nav_home: //for home class
                        selectedFragment = new FragmentHome();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                        break;
                    case R.id.nav_fav: //for weather activity
                        selectedFragment = new FragmentWeather();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                        break;

//                    case R.id.nav_more: //for bottom sheet
//                        BottomSheetMore bottomSheetMore = new BottomSheetMore();
//                        bottomSheetMore.show(getSupportFragmentManager(), "More");
//                        break;
                }
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();

    }
}