package com.amidezcod.impulse2k18.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.amidezcod.impulse2k18.adapter.ViewPagerAdapterAbout;
import com.amidezcod.impulse2k18.fragments.AboutChildrenEducationSociety;
import com.amidezcod.impulse2k18.fragments.AboutCollege;
import com.amidezcod.impulse2k18.fragments.AboutImpulse;
import com.amidezcod.impulse2k18.fragments.Social;

import impulse2k18.R;


public class AboutActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private LinearLayout linearLayout;
    boolean onlyOneTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        viewPager = findViewById(R.id.tab_viewpager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        linearLayout = findViewById(R.id.linearlayouthide);
        Toolbar toolbar = findViewById(R.id.toolbar_about);
        collapsingToolbarLayout = findViewById(R.id.htab_collapse_toolbar);
        appBarLayout = findViewById(R.id.htab_appbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setTitle("About Us");
            getSupportActionBar().setTitle("About Us");
        }

        if (viewPager != null) {
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                    if (onlyOneTime) {
                        appBarLayout.animate().setDuration(100).start();
                        appBarLayout.setExpanded(false, true);
                        onlyOneTime = false;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        }
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (verticalOffset == 0)
                linearLayout.setVisibility(View.VISIBLE);
            else
                linearLayout.setVisibility(View.INVISIBLE);
        });
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapterAbout viewPagerAdapterAbout = new ViewPagerAdapterAbout(getSupportFragmentManager());
        viewPagerAdapterAbout.addFragments(new AboutImpulse(), "Impulse");
        viewPagerAdapterAbout.addFragments(new AboutChildrenEducationSociety(), "Children Edu. Society");
        viewPagerAdapterAbout.addFragments(new AboutCollege(), "T.O.C.E");
        viewPagerAdapterAbout.addFragments(new Social(), "Socials");
        viewPager.setAdapter(viewPagerAdapterAbout);
    }
}
