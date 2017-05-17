package com.zhoujf.news.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.leon.loopviewpagerlib.FunBanner;
import com.zhoujf.news.R;


/**
 * Created by ZhouJF on 2017-05-17.
 */

public class NewsPullToRefresh extends PullToRefreshListView {
    private int[] RESID = {R.mipmap.news1, R.mipmap.news2, R.mipmap.news3, R.mipmap.news4};

    public NewsPullToRefresh(Context context) {
        this(context, null);
    }

    public NewsPullToRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        setAdapter(adapter);
        setMode(Mode.BOTH);
        FunBanner.Builder builder = new FunBanner.Builder(getContext());
        FunBanner mFunBanner = builder.setEnableAutoLoop(true)
                .setImageResIds(RESID)
                .setDotSelectedColor(Color.RED)
                .setHeightWidthRatio(0.5f)
                .build();
        getRefreshableView().addHeaderView(mFunBanner);

    }

    private BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if(convertView == null){

                convertView = View.inflate(getContext(), R.layout.item_news, null);
                holder = new Holder(convertView);
            }else{
                holder = (Holder) convertView.getTag();
            }
            return convertView;
        }
    };

    static class Holder {
        TextView title;
        ImageView img;
        ImageView comment;
        TextView date;

        public Holder(View v) {
            title = (TextView) v.findViewById(R.id.tv_newspage_title);
            date = (TextView) v.findViewById(R.id.tv_newspage_date);
            img = (ImageView) v.findViewById(R.id.iv_newspage_img);
            comment = (ImageView) v.findViewById(R.id.iv_newspage_comment);
        }
    }
}
