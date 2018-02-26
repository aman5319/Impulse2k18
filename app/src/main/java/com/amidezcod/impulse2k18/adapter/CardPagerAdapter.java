package com.amidezcod.impulse2k18.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amidezcod.impulse2k18.activity.EventDetailsActivity;
import com.amidezcod.impulse2k18.activity.MainActivity;
import com.amidezcod.impulse2k18.modal.CardItem;
import impulse2k18.R;


import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;
    private MainActivity mainActivity;

    public CardPagerAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        mData = new ArrayList<>();
        mViews = new ArrayList<>();

    }

    public static String EVENT_DETAILS_INTENT = "eventsDetails";
    public static String EVENT_IMAGE_INTENT = "image";
    public static String EVENT_TEXT_INTENT = "name";


    public void addCardItem(CardItem item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position == 12 || position == 13) {
            return null;
        }
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.adapter, container, false);
        container.addView(view);
        bind(mData.get(position), view, container.getContext());
        CardView cardView = view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }
        cardView.setMaxCardElevation(2 * mBaseElevation + MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }


    private void bind(CardItem item, View view, final Context context) {
        ImageView imageView = view.findViewById(R.id.event_poster);
        TextView eventQuotes = view.findViewById(R.id.event_quote);
        imageView.setBackgroundResource(item.getImageId());
        eventQuotes.setText(item.getQuotes());
        view.setOnClickListener(v -> {

            Intent intent = new Intent(context, EventDetailsActivity.class);
            intent.putExtra(EVENT_DETAILS_INTENT, item.getEventDetailsInfo());
            intent.putExtra(EVENT_IMAGE_INTENT, item.getImageId());
            intent.putExtra(EVENT_TEXT_INTENT, item.getText());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Pair imagepair = Pair.create((View) imageView, "tImage");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(mainActivity, imagepair);
                ActivityCompat.startActivity(context, intent, activityOptions.toBundle());

            } else {
                context.startActivity(intent);
            }
        });
    }

}
