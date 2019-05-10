package com.example.onboardingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.onboardingapp.R;

public class SliderAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public SliderAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public int[] mSlideImages = {
            R.drawable.group_10,
            R.drawable.group_11,
            R.drawable.group_12
    };

    public String[] mSlideHeadings = {
            "Eat",
            "Sleep",
            "Code"
    };

    public String[] mSlideDescription = {
            "It is a long established fact that a reader will be distracted by the readable content " +
                    "of a page when looking at its layout.",
            "It is a long established fact that a reader will be distracted by the readable content " +
                    "of a page when looking at its layout.",
            "It is a long established fact that a reader will be distracted by the readable content " +
                    "of a page when looking at its layout."

    };

    @Override
    public int getCount() {
        return mSlideHeadings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.image_view_icon);
        TextView headingTextView = view.findViewById(R.id.text_view_heading);
        TextView descriptionTextView =view.findViewById(R.id.text_view_description);

        slideImageView.setImageResource(mSlideImages[position]);
        headingTextView.setText(mSlideHeadings[position]);
        descriptionTextView.setText(mSlideDescription[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
