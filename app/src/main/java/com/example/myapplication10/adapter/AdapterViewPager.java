package com.example.myapplication10.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication10.fragment.FragmentHistory;
import com.example.myapplication10.fragment.FragmentSearch;
import com.example.myapplication10.fragment.FragmentToday;

public class AdapterViewPager extends FragmentStatePagerAdapter {

    private int numPage;

    public AdapterViewPager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numPage = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentToday();
            case 1:
                return new FragmentHistory();
            case 2:
                return new FragmentSearch();
        }
        return new FragmentToday();
    }

    @Override
    public int getCount() {
        return numPage;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Today";
            case 1: return "History";
            case 2: return "Search";
        }
        return super.getPageTitle(position);
    }
}
