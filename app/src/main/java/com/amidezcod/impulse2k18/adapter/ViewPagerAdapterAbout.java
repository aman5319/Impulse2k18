package com.amidezcod.impulse2k18.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amidezcod on 17/6/17.
 */

public class ViewPagerAdapterAbout extends FragmentPagerAdapter {
    private final List<Fragment> listOfFragments = new ArrayList<>();
    private final List<String> fragmentTitle = new ArrayList<>();


    public ViewPagerAdapterAbout(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listOfFragments.get(position);
    }

    @Override
    public int getCount() {
        return listOfFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }

    public void addFragments(Fragment fragment, String s) {
        listOfFragments.add(fragment);
        fragmentTitle.add(s);
    }
}
