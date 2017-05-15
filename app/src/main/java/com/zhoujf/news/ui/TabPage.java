package com.zhoujf.news.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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

class TabPage extends RelativeLayout {
    @BindView(R.id.tab_page_menu)
    ImageView mTabPageMenu;
    @BindView(R.id.tab_pag_title)
    TextView mTabPagTitle;

    public TabPage(Context context) {
        this(context, null);
    }

    public TabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();


    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_tab_page, this);
        ButterKnife.bind(this, this);
    }

    public void hideMenu() {
        mTabPageMenu.setVisibility(GONE);
    }

    @OnClick(R.id.tab_page_menu)
    public void onClick() {
    }
    public void setTitle(String title){
        mTabPagTitle.setText(title);
    }
}
