package com.zhoujf.news.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viewpagerindicator.TabPageIndicator;
import com.zhoujf.news.R;

/**
 * Created by ZhouJF on 2017-05-16.
 */

public class TextIndicator extends LinearLayout {
    private static final String[] CONTENT = new String[]{"Recent", "Artists", "Albums", "Songs", "Playlists", "Genres"};
    private Context mContext;

    public TextIndicator(Context context) {
        this(context, null);
    }

    public TextIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        View view = View.inflate(context, R.layout.simple_tabs, this);


        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }

    private PagerAdapter adapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return CONTENT.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView tv = new TextView(mContext);
            container.addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position];
        }
    };


}
