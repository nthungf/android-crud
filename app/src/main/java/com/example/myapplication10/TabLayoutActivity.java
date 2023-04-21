package com.example.myapplication10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication10.adapter.AdapterViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private AdapterViewPager adapterViewPager;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        initViews();
        adapterViewPager = new AdapterViewPager(getSupportFragmentManager(), 3);
//        viewPager.setPageTransformer(true, new HorizontalFlipTransformation());
        viewPager.setAdapter(adapterViewPager);
        tabLayout.setupWithViewPager(viewPager);

        fab = findViewById(R.id.floatingButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TabLayoutActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initViews() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
    }

    @Override
    public void onClick(View v) {

    }
}