package com.example.appmath2023.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.appmath2023.R;
import com.example.appmath2023.databinding.ActivityMainBinding;
import com.example.appmath2023.view.adapter.ViewPagerAdapter;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        setUpViewPager();
        mainBinding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_plus:
                        mainBinding.viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_minus:
                        mainBinding.viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_multyply:
                        mainBinding.viewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_division:
                        mainBinding.viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(MainActivity.this);
        mainBinding.viewPager.setAdapter(viewPagerAdapter);
    }
}