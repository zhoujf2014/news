package com.zhoujf.news.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.viewpagerindicator.CirclePageIndicator;
import com.zhoujf.news.R;
import com.zhoujf.news.util.SP;

/**
 * Created by ZhouJF on 2017-05-15.
 */

public  class NavigationActivity extends BaseActivity{

    private ViewPager mViewPager;
    private int[] NAVIGATIONIMAGES = {R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3};
    private CirclePageIndicator mIndicator;
    private Button mButton;
    private Context mContext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_navigation;
    }

    @Override
    public void init() {
        super.init();
        mContext = this;
        mViewPager = (ViewPager) findViewById(R.id.vp_navigation);
        mButton = (Button) findViewById(R.id.bt_naviga);
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.addOnPageChangeListener(pagerListener);
        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mViewPager);
        mButton.setOnClickListener(clickListener);


    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SP.setBooleanData("IsFirstInstall",true,mContext);
            startActivity(MainActivity.class);
            finish();
        }
    };
    private  ViewPager.OnPageChangeListener pagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if(position == NAVIGATIONIMAGES.length-1){
                mButton.setVisibility(View.VISIBLE);
            }else {
                mButton.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private PagerAdapter viewPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return NAVIGATIONIMAGES.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(NavigationActivity.this);
            imageView.setBackgroundResource(NAVIGATIONIMAGES[position]);
            container.addView(imageView);
             return  imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    };
}
