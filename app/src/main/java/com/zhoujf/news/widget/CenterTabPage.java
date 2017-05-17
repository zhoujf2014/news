package com.zhoujf.news.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.zhoujf.news.R;
import com.zhoujf.news.ui.BaseTabPage;

import butterknife.ButterKnife;

/**
 * Created by ZhouJF on 2017-05-16.
 */

public class CenterTabPage extends BaseTabPage {
    private static final String TAG = "CenterTabPage";
    public CenterTabPage(Context context) {
        super(context);
    }

    public CenterTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);


    }




    @Override
    public void setItemClick(int position) {
        switch (position) {
            case 0:
                TextIndicator textIndicator = new TextIndicator(getContext());
                mFlContent.addView(textIndicator);
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
