package com.amidezcod.impulse2k18.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amidezcod.impulse2k18.adapter.CardPagerAdapter;
import com.amidezcod.impulse2k18.adapter.ShadowTransformer;
import com.amidezcod.impulse2k18.modal.DataForEvents;

import impulse2k18.R;


public class MainActivity extends AppCompatActivity {

    public TextView toolbarText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ViewPager mViewPager = findViewById(R.id.viewPager);
        LinearLayout linearLayout = findViewById(R.id.layout);
        toolbarText = findViewById(R.id.toolbar_text);
        CardPagerAdapter mCardAdapter = new CardPagerAdapter(MainActivity.this);

        mCardAdapter = DataForEvents.enterData(mCardAdapter);

        ShadowTransformer mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter, linearLayout, toolbarText);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mCardShadowTransformer.enableScaling(true);
    }
}
