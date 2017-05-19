package com.zhoujf.news.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.zhoujf.news.R;
import com.zhoujf.news.bean.PhotoPageBean;
import com.zhoujf.news.network.JsonBeanRequest;
import com.zhoujf.news.network.JsonBeanRequestListener;
import com.zhoujf.news.network.NetworkManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZhouJF on 2017-05-19.
 */

public class PhotoPage extends RelativeLayout {
    private static final String TAG = "PhotoPage";
    @BindView(R.id.lv_photopage)
    ListView mLvPhotopage;
    @BindView(R.id.gv_photopage)
    GridView mGvPhotopage;
    private List<PhotoPageBean.DataBean.NewsBean> mNewsBeen;

    public PhotoPage(Context context) {
        this(context, null);
    }

    public PhotoPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.photopage, this);
        ButterKnife.bind(this, this);
        loadDataFromServer();

    }

    private void loadDataFromServer() {
        String url = "http://192.168.21.99:8080/zhbj/photos/photos_1.json";
        JsonBeanRequest<PhotoPageBean> photoPageBeanJsonBeanRequest = new JsonBeanRequest<PhotoPageBean>(PhotoPageBean.class, url, new JsonBeanRequestListener<PhotoPageBean>() {
            @Override
            public void onResponse(PhotoPageBean response) {
                mNewsBeen = response.getData().getNews();
                mLvPhotopage.setAdapter(mAdapter);
                mGvPhotopage.setAdapter(mAdapter);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.getNetworkTimeMs();
            }
        });
        NetworkManager.sRequestQueue.add(photoPageBeanJsonBeanRequest);


    }

    private BaseAdapter mAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            if (mNewsBeen == null) {
                return 0;
            }
            return mNewsBeen.size();
        }

        @Override
        public Object getItem(int position) {
            return mNewsBeen.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.item_photopage, null);
                holder = new Holder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            PhotoPageBean.DataBean.NewsBean newsBean = mNewsBeen.get(position);
            holder.title.setText(newsBean.getTitle());
            holder.mImage.setImageUrl(newsBean.getListimage(), NetworkManager.getImageLoader());
            return convertView;
        }
    };

    public void setshowType(boolean showType) {
        if (showType) {
            mGvPhotopage.setVisibility(View.VISIBLE);
            mLvPhotopage.setVisibility(View.GONE);
        } else {
            mLvPhotopage.setVisibility(View.VISIBLE);
            mGvPhotopage.setVisibility(View.GONE);
        }
    }


    static class Holder {
        TextView title;
        NetworkImageView mImage;

        Holder(View v) {
            mImage = (NetworkImageView) v.findViewById(R.id.niv_photopage);
            title = (TextView) v.findViewById(R.id.tv_photopage);

        }
    }
}
