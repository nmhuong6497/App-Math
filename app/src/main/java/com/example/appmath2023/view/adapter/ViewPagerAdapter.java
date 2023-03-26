package com.example.appmath2023.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appmath2023.view.fragment.DivisionFragment;
import com.example.appmath2023.view.fragment.MinusFragment;
import com.example.appmath2023.view.fragment.MultiplyFragment;
import com.example.appmath2023.view.fragment.PlusFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PlusFragment();
            case 1:
                return new MinusFragment();
            case 2:
                return new MultiplyFragment();
            case 3:
                return new DivisionFragment();
            default:
                return new PlusFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
