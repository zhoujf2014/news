package com.zhoujf.news.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhoujf.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZhouJF on 2017-05-15.
 */

public class LeftMenuFragment extends Fragment {
    private static final String TAG = "LeftMenuFragment";

    @BindView(R.id.lv_leftfragment)
    ListView mLvLeftfragment;
    private String[] menus = {"新闻", "专题", "组图", "互动"};
    private OnMenuItemClickListener mOnMenuItemClickListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left_menu, null);
        ButterKnife.bind(this, view);
        init();
        return view;

    }

    private void init()
    {
        mLvLeftfragment.setAdapter(mLvLeftfragmentAdapter);
        mLvLeftfragment.setOnItemClickListener(mLvLeftfragmentClickListener);
    }
    private AdapterView.OnItemClickListener mLvLeftfragmentClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mOnMenuItemClickListener.onItemClick(position);
            parent.getChildAt(position).setEnabled(true);
            Log.d(TAG, "onItemClick:条目被点击 " + position);
        }
    };
    private BaseAdapter mLvLeftfragmentAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return menus.length;
        }

        @Override
        public Object getItem(int position) {
            return menus[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(getContext(),R.layout.item_left_lv, null);
            TextView tv = (TextView) convertView.findViewById(R.id.tv_left);
            convertView.setEnabled(false);
            tv.setText(menus[position]);
            return convertView;
        }
    };
    public void setOnMenuItemClickListener(OnMenuItemClickListener listener){
        mOnMenuItemClickListener = listener;
    }
    public interface OnMenuItemClickListener{
        void onItemClick(int position);
    }
}
