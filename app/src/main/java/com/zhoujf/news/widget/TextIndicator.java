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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.viewpagerindicator.TabPageIndicator;
import com.zhoujf.news.R;
import com.zhoujf.news.bean.CatagreBean;

import java.util.List;

/**
 * Created by ZhouJF on 2017-05-16.
 */

public class TextIndicator extends LinearLayout {
    private static final String TAG = "TextIndicator";
    private Context mContext;
    private List<CatagreBean.DataBean.ChildrenBean> mDataBean;
    private TabPageIndicator mIndicator;


    public TextIndicator(Context context) {
        this(context, null);
    }


    public TextIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        View view = View.inflate(context, R.layout.simple_tabs, this);


        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        mIndicator = (TabPageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(pager);
    }

    private PagerAdapter adapter = new PagerAdapter() {
        @Override
        public int getCount() {
            if (mDataBean == null) {
                return 0;
            }
            Log.d(TAG, "getCount: " + mDataBean.size());
            return mDataBean.size();

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            NewsPullToRefresh newsPullToRefresh = new NewsPullToRefresh(getContext());

            String url = mDataBean.get(position).getUrl();
            newsPullToRefresh.setData(url);

            container.addView(newsPullToRefresh);
            return newsPullToRefresh;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mDataBean.get(position).getTitle();
        }
    };




    public void setData(List<CatagreBean.DataBean.ChildrenBean> dataBean) {
        mDataBean = dataBean;
        mIndicator.notifyDataSetChanged();
        adapter.notifyDataSetChanged();

    }
}
