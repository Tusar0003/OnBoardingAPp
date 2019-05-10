package com.example.onboardingapp.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.onboardingapp.R;
import com.example.onboardingapp.adapter.SliderAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter mAdapter;

    private TextView[] mDots;
    private Button mPreviousButton;
    private Button mNextButton;

    private int mCurrentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.view_pager);
        mDotLayout = findViewById(R.id.layout_dot);

        mPreviousButton = findViewById(R.id.button_previous);
        mNextButton = findViewById(R.id.button_next);

        mAdapter = new SliderAdapter(this);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(mOnPageChangeListener);

        findViewById(R.id.button_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

        findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mNextButton.getText().toString().equalsIgnoreCase("Finish")) {
                    finish();
                }

                mViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        addDotsIndicator(0);
    }

    private void addDotsIndicator(int position) {
        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i=0;i<mDots.length;i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage = i;

            if (i == 0) {
                mNextButton.setEnabled(true);
                mPreviousButton.setEnabled(false);
                mPreviousButton.setVisibility(View.GONE);
            } else if (i == mDots.length - 1) {
                mNextButton.setEnabled(true);
                mPreviousButton.setEnabled(true);
                mPreviousButton.setVisibility(View.VISIBLE);

                mNextButton.setText("Finish");
            } else {
                mNextButton.setEnabled(true);
                mPreviousButton.setEnabled(true);
                mPreviousButton.setVisibility(View.VISIBLE);

                mNextButton.setText("Next");
                mPreviousButton.setText("Previous");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
