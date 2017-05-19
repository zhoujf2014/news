package com.zhoujf.news.ui;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zhoujf.news.R;
import com.zhoujf.news.widget.AffairTabPage;
import com.zhoujf.news.widget.CenterTabPage;
import com.zhoujf.news.widget.HomeTabPage;
import com.zhoujf.news.widget.ServiceTabPage;
import com.zhoujf.news.widget.SettingTabPage;

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
    private SparseArray<BaseTabPage> sa = new SparseArray<>();
    private View mRoot;
    private BaseTabPage mTtabPage;
    private static final String TAG = "ContentFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_content, null);
        ButterKnife.bind(this, mRoot);
        init();

        return mRoot;
    }

    private void init() {

        mTabContainer.setOnCheckedChangeListener(mOnCheckedChangeListener);
        mTabContainer.check(R.id.tab_home);
    }


    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            BaseTabPage tabPage = null;
            if (sa.get(checkedId) != null) {
                tabPage = sa.get(checkedId);
            } else {


                switch (checkedId) {
                    case R.id.tab_home:
                        tabPage = new HomeTabPage(getContext());
                        tabPage.hideMenu();
                        tabPage.setTitle("首页");
                        break;
                    case R.id.tab_news_center:
                        tabPage = new CenterTabPage(getContext());
                        tabPage.setTitle("新闻中心");

                        break;
                    case R.id.tab_smart_service:
                        tabPage = new ServiceTabPage(getContext());
                        tabPage.setTitle("智慧服务");

                        break;
                    case R.id.tab_gov_affairs:
                        tabPage = new AffairTabPage(getContext());
                        tabPage.setTitle("政务");
                        break;
                    case R.id.tab_settings:
                        tabPage = new SettingTabPage(getContext());
                        tabPage.hideMenu();
                        tabPage.setTitle("设置中心");
                        break;
                }

                sa.append(checkedId,tabPage);
            }
            Log.d(TAG, "onCheckedChanged: 新闻中心，即将加载网路数据");
            tabPage.loadDataFromServer();
            tabPage.setOnMenuClickListener(new BaseTabPage.OnMenuClickListener() {
                @Override
                public void onMenuClick() {
                    if (onContenStateChangeListener != null){

                        onContenStateChangeListener.onMenuClick();
                    }
                }
            });
            mTabPageContainer.removeAllViews();
            mTabPageContainer.addView(tabPage);
           mTtabPage = tabPage;
            if (onContenStateChangeListener != null){

            onContenStateChangeListener.onTabSwitch(checkedId);
            }
        }
    };

    public void setOnContenStateChangeListener(OnContenStateChangeListener onContenStateChangeListener) {
        this.onContenStateChangeListener = onContenStateChangeListener;
    }

    public void setOnitemClick(int position) {
        mTtabPage.setItemClick(position);

    }

    interface OnContenStateChangeListener {
        void onTabSwitch(int cheackId);
        void onMenuClick();
    }
}
