package com.example.appmath2023.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.appmath2023.R;
import com.example.appmath2023.databinding.ActivityLaucherBinding;

public class LaucherActivity extends AppCompatActivity {
    ActivityLaucherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLaucherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LaucherActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}