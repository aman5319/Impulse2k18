package com.amidezcod.impulse2k18.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import impulse2k18.R;


public class ShadowTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private ViewPager mViewPager;
    private CardAdapter mAdapter;
    private float mLastOffset;
    private boolean mScalingEnabled;
    LinearLayout linearLayout;
    TextView toolbar_text;
    boolean showfirst = true;

    public ShadowTransformer(ViewPager viewPager, CardAdapter adapter, LinearLayout linearLayout, TextView toolbar_text) {
        mViewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
        mAdapter = adapter;
        this.linearLayout = linearLayout;
        this.toolbar_text = toolbar_text;
    }

    public void enableScaling(boolean enable) {
        if (mScalingEnabled && !enable) {
            // shrink main card
            CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
            if (currentCard != null) {
                currentCard.animate().scaleY(1);
                currentCard.animate().scaleX(1);
            }
        } else if (!mScalingEnabled && enable) {
            // grow main card
            CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
            if (currentCard != null) {
                currentCard.animate().scaleY(1.1f);
                currentCard.animate().scaleX(1.1f);
            }
        }

        mScalingEnabled = enable;
    }

    @Override
    public void transformPage(View page, float position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int realCurrentPosition;
        int nextPosition;
        float baseElevation = mAdapter.getBaseElevation();
        float realOffset;
        boolean goingLeft = mLastOffset > positionOffset;

        // If we're going backwards, onPageScrolled receives the last position
        // instead of the current one
        if (goingLeft) {
            realCurrentPosition = position + 1;
            nextPosition = position;
            realOffset = 1 - positionOffset;
        } else {
            nextPosition = position + 1;
            realCurrentPosition = position;
            realOffset = positionOffset;
        }

        // Avoid crash on overscroll
        if (nextPosition > mAdapter.getCount() - 1
                || realCurrentPosition > mAdapter.getCount() - 1) {
            return;
        }

        CardView currentCard = mAdapter.getCardViewAt(realCurrentPosition);

        // This might be null if a fragment is being used
        // and the views weren't created yet
        if (currentCard != null) {
            if (mScalingEnabled) {
                currentCard.setScaleX((float) (1 + 0.1 * (1 - realOffset)));
                currentCard.setScaleY((float) (1 + 0.1 * (1 - realOffset)));
            }
            currentCard.setCardElevation((baseElevation + baseElevation
                    * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)));
        }

        CardView nextCard = mAdapter.getCardViewAt(nextPosition);

        // We might be scrolling fast enough so that the next (or previous) card
        // was already destroyed or a fragment might not have been created yet
        if (nextCard != null) {
            if (mScalingEnabled) {
                nextCard.setScaleX((float) (1 + 0.1 * (realOffset)));
                nextCard.setScaleY((float) (1 + 0.1 * (realOffset)));
            }
            nextCard.setCardElevation((baseElevation + baseElevation
                    * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (realOffset)));
        }

        mLastOffset = positionOffset;
        Log.v("TAG", String.valueOf(position));
        if (position == 0 && showfirst) {
            showfirst = false;
            linearLayout.setBackground(ContextCompat.getDrawable(linearLayout.getContext(), R.drawable.coding_grad));
            toolbar_text.setText("#WebDesign");

        }
    }

    @Override
    public void onPageSelected(int position) {
        backgroundColor(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void backgroundColor(int position) {
        int color;
        String text;
        switch (position) {
            case 0:
                color = R.drawable.coding_grad;
                text = "#WebDesign";
                break;
            case 1:
                color = R.drawable.debate_grad;
                text = "#Coding";
                break;
            case 2:
                color = R.drawable.entertainment_grad;
                text = "#Debate";
                break;
            case 3:
                color = R.drawable.web_grad;
                text = "#EntertainmentQuiz";
                break;
            case 4:
                color = R.drawable.debate_grad;
                text = "#FootLoose";
                break;
            case 5:
                color = R.drawable.coding_grad;
                text = "#Gaming";
                break;
            case 6:
                color = R.drawable.entertainment_grad;
                text = "#ITQuiz";
                break;
            case 7:
                color = R.drawable.debate_grad;
                text = "#NammaKannada";
                break;
            case 8:
                color = R.drawable.web_grad;
                text = "#MovieMaking";
                break;
            case 9:
                color = R.drawable.coding_grad;
                text = "#Photography";
                break;
            case 10:
                color = R.drawable.entertainment_grad;
                text = "#TreasureHunt";
                break;
            case 11:
                color = R.drawable.debate_grad;
                text = "#Buzzinga";
                break;
            default:
                color = R.drawable.web_grad;
                text = "";
                break;
        }

        toolbar_text.setText(text);

        linearLayout.setBackground(ContextCompat.getDrawable(linearLayout.getContext(), color));
    }

}
