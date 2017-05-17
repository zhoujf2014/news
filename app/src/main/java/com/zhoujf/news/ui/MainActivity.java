package com.zhoujf.news.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.zhoujf.news.R;

public class MainActivity extends SlidingFragmentActivity{
    private static final String TAG = "MainActivity";
private ContentFragment mContentFragment;
    private LeftMenuFragment mLeftMenuFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentFragment();
        initLeftMenu();
        initSlidingMenu();
        initContent();

        init();
    }

    private void init() {
        mLeftMenuFragment.setOnMenuItemClickListener(new LeftMenuFragment.OnMenuItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onItemClick: ");
                toggle();
                mContentFragment.setOnitemClick(position);
            }
        });
    }

    private void initContent() {
        setContentView(R.layout.content_frame);
        getSupportFragmentManager()
                .beginTransaction()
              .replace(R.id.content_frame,mContentFragment)
                 .commit();
    }

    private void initSlidingMenu() {
        SlidingMenu sm = getSlidingMenu();
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setMode(SlidingMenu.LEFT);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
    }

    private void initLeftMenu() {
        setBehindContentView(R.layout.menu_frame);
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        mLeftMenuFragment = new LeftMenuFragment();
        t.replace(R.id.menu_frame, mLeftMenuFragment);
        t.commit();

    }

    private void initContentFragment() {
        mContentFragment = new ContentFragment();

        mContentFragment.setOnContenStateChangeListener(new ContentFragment.OnContenStateChangeListener() {
            @Override
            public void onTabSwitch(int cheackId) {
                if (cheackId==R.id.tab_home||cheackId == R.id.tab_settings){
                  getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }else {
                    getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
                    //test

                }
            }

            @Override
            public void onMenuClick() {
                toggle();
            }
        });

    }
}
