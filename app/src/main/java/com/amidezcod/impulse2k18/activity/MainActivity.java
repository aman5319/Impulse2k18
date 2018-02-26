package com.amidezcod.impulse2k18.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amidezcod.impulse2k18.adapter.CardPagerAdapter;
import com.amidezcod.impulse2k18.adapter.ShadowTransformer;
import com.amidezcod.impulse2k18.modal.DataForEvents;
import impulse2k18.R;


public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    public TextView toolbarText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.viewPager);
        LinearLayout linearLayout = findViewById(R.id.layout);
        toolbarText = findViewById(R.id.toolbar_text);
        mCardAdapter = new CardPagerAdapter(MainActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mCardAdapter = DataForEvents.enterData(mCardAdapter);

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter, linearLayout, toolbarText );

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mCardShadowTransformer.enableScaling(true);
    }
}
