package com.zhoujf.news.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.zhoujf.news.ui.BaseTabPage;

/**
 * Created by ZhouJF on 2017-05-16.
 */

public class HomeTabPage extends BaseTabPage {
    private static final String TAG = "HomeTabPage";
    public HomeTabPage(Context context) {
        super(context);
    }

    public HomeTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setItemClick(int position) {
        switch (position) {
            case 0:
                Log.d(TAG, "setItemClick: 新闻0");
                break;
            case 1:
                Log.d(TAG, "setItemClick: 新闻1");
                break;
            case 2:
                Log.d(TAG, "setItemClick: 新闻2");
                break;
            case 3:
                Log.d(TAG, "setItemClick: 新闻3");
                break;
            case 4:
                Log.d(TAG, "setItemClick: 新闻4");
                break;
        }
    }
}
