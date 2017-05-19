package com.zhoujf.news.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
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
    @BindView(R.id.fl_content)
    public FrameLayout mFlContent;
    @BindView(R.id.iv_pic_type)
   public ImageView mIvPicType;

    private OnMenuClickListener mOnMenuClickListener;

    public BaseTabPage(Context context) {
        this(context, null);
    }

    public BaseTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();


    }

    protected void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_tab_page, this);
        ButterKnife.bind(this, this);
    }

    public void hideMenu() {
        mTabPageMenu.setVisibility(View.GONE);
    }

    public void setTitle(String title) {
        mTabPagTitle.setText(title);
    }


    public void setOnMenuClickListener(OnMenuClickListener listener) {
        mOnMenuClickListener = listener;
    }

    public void setItemClick(int position) {


    }
    public void loadDataFromServer() {
    }




    @OnClick({R.id.tab_page_menu, R.id.iv_pic_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_page_menu:
                mOnMenuClickListener.onMenuClick();
                break;
            case R.id.iv_pic_type:

                break;
        }
    }

    public void setTypevisible(int gone) {

    }

    public interface OnMenuClickListener {
        void onMenuClick();
    }
}
