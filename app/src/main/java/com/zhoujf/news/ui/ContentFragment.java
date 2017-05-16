package com.zhoujf.news.ui;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.support.v4.app.Fragment;
;

import com.zhoujf.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZhouJF on 2017-05-15.
 */

public class ContentFragment extends android.support.v4.app.Fragment {
    @BindView(R.id.tab_page_container)
    FrameLayout mTabPageContainer;
    @BindView(R.id.tab_home)
    RadioButton mTabHome;
    @BindView(R.id.tab_news_center)
    RadioButton mTabNewsCenter;
    @BindView(R.id.tab_smart_service)
    RadioButton mTabSmartService;
    @BindView(R.id.tab_gov_affairs)
    RadioButton mTabGovAffairs;
    @BindView(R.id.tab_settings)
    RadioButton mTabSettings;
    @BindView(R.id.tab_container)
    RadioGroup mTabContainer;
    private OnContenStateChangeListener onContenStateChangeListener;
    private SparseArray<TabPage> sa = new SparseArray<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_content, null);
        ButterKnife.bind(this, root);
        init();

        return root;
    }

    private void init() {
        mTabContainer.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            TabPage tabPage = null;
            if (sa.get(checkedId) != null) {
                tabPage = sa.get(checkedId);
            } else {
                tabPage = new TabPage(getContext());

                switch (checkedId) {
                    case R.id.tab_container:
                    tabPage.hideMenu();
                        tabPage.setTitle("首页");
                        break;
                    case R.id.tab_news_center:
                        tabPage.setTitle("新闻中心");
                        break;
                    case R.id.tab_smart_service:
                        tabPage.setTitle("智慧服务");
                        break;
                    case R.id.tab_gov_affairs:
                        tabPage.setTitle("政务");
                        break;
                    case R.id.tab_settings:
                        tabPage.hideMenu();
                        tabPage.setTitle("设置中心");
                        break;
                }
            }
            tabPage.setOnMenuClickListener(new TabPage.OnMenuClickListener() {
                @Override
                public void onMenuClick() {
                    if (onContenStateChangeListener != null){

                        onContenStateChangeListener.onMenuClick();
                    }
                }
            });
            mTabPageContainer.removeAllViews();
            mTabPageContainer.addView(tabPage);
            if (onContenStateChangeListener != null){

            onContenStateChangeListener.onTabSwitch(checkedId);
            }
        }
    };

    public void setOnContenStateChangeListener(OnContenStateChangeListener onContenStateChangeListener) {
        this.onContenStateChangeListener = onContenStateChangeListener;
    }

    interface OnContenStateChangeListener {
        void onTabSwitch(int cheackId);
        void onMenuClick();
    }
}
