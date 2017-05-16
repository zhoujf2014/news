package com.zhoujf.news.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhoujf.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZhouJF on 2017-05-15.
 */

public class BaseTabPage extends RelativeLayout {
    @BindView(R.id.tab_page_menu)
    ImageView mTabPageMenu;
    @BindView(R.id.tab_pag_title)
    TextView mTabPagTitle;
    @BindView(R.id.tv_text)
    TextView mTvText;
    private OnMenuClickListener mOnMenuClickListener;

    public BaseTabPage(Context context) {
        this(context, null);
    }

    public BaseTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();


    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_tab_page, this);
        ButterKnife.bind(this, this);
    }

    public void hideMenu() {
        mTabPageMenu.setVisibility(View.GONE);
    }
    public void setTitle(String title) {
        mTabPagTitle.setText(title);
    }

    @OnClick(R.id.tab_page_menu)
    public void onClick() {
        mOnMenuClickListener.onMenuClick();
    }



    public void setOnMenuClickListener(OnMenuClickListener listener) {
        mOnMenuClickListener = listener;

    }

    public void setItemClick(int position) {
        switch (position){
            case 0:mTvText.setText("0");break;
            case 1:mTvText.setText("1");break;
            case 2:mTvText.setText("2");break;
            case 3:mTvText.setText("3");break;
            case 4:mTvText.setText("4");break;
        }

    }

    public interface OnMenuClickListener {
        void onMenuClick();
    }
}
