package com.zhoujf.news.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.leon.loopviewpagerlib.FunBanner;
import com.zhoujf.news.R;
import com.zhoujf.news.bean.NewsItemBean;
import com.zhoujf.news.network.JsonBeanRequest;
import com.zhoujf.news.network.JsonBeanRequestListener;
import com.zhoujf.news.network.NetworkManager;
import com.zhoujf.news.util.SP;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ZhouJF on 2017-05-17.
 */

public class NewsPullToRefresh extends PullToRefreshListView {
    private static final String TAG = "NewsPullToRefresh";


    private List<NewsItemBean.DataBean.NewsBean> mNewsBean;
    private String mString;
    private String mHeadPath = "http://192.168.21.99:8080/zhbj";
    private NewsItemBean mNewsItemBean;
    private String mUrl;
    private FunBanner mFunBanner;
    private List<String> mResUrl;

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
        mFunBanner = builder.setEnableAutoLoop(true)
                .setDotSelectedColor(Color.RED)
                .setHeightWidthRatio(0.5f)
                .build();

        getRefreshableView().addHeaderView(mFunBanner);
        setOnRefreshListener(mOnRefreshListener2);
        setOnItemClickListener(mOnItemClickListener);

    }

    private OnRefreshListener2 mOnRefreshListener2 = new OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase pullToRefreshBase) {

            setData(mUrl);
            onRefreshComplete();
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase pullToRefreshBase) {
            String moreUrl = mNewsItemBean.getData().getCountcommenturl();
            if(moreUrl.length() < 1){

               postDelayed(new Runnable() {
                   @Override
                   public void run() {
                      onRefreshComplete();
                   }
               },300);

            }else {
                loadMoreData();
            }
        }
    };

    private void loadMoreData() {
        String path = mHeadPath + mNewsItemBean.getData().getMore();
        Log.d(TAG, "loadMoreData: " + path);
        JsonBeanRequest<NewsItemBean> beanRequest = new JsonBeanRequest<NewsItemBean>(NewsItemBean.class, path,
                new JsonBeanRequestListener<NewsItemBean>() {
                    @Override
                    public void onResponse(NewsItemBean response) {
                        mNewsBean.addAll(response.getData().getNews());
                        mNewsItemBean = response;

                        adapter.notifyDataSetChanged();

                    }
                });
        NetworkManager.sRequestQueue.add(beanRequest);
    }

    private BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            if (mNewsBean == null) {
                return 0;
            }
            return mNewsBean.size();
        }

        @Override
        public Object getItem(int position) {
            return mNewsBean.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {

                convertView = View.inflate(getContext(), R.layout.item_news, null);
                holder = new Holder(convertView);
                convertView.setTag(holder);

            } else {
                holder = (Holder) convertView.getTag();
            }
            NewsItemBean.DataBean.NewsBean newsBean = mNewsBean.get(position);
            boolean booleanData = SP.getBooleanData(newsBean.getTitle(), getContext());
            holder.title.setTextColor(booleanData?getResources().getColor(R.color.gray):Color.BLACK);
            holder.date.setText(newsBean.getPubdate());
            holder.title.setText(newsBean.getTitle());
           holder.img.setImageUrl(newsBean.getListimage(),NetworkManager.getImageLoader());
            return convertView;
        }
    };

    static class Holder {
        TextView title;
        NetworkImageView img;
        ImageView comment;
        TextView date;

        Holder(View v) {
            title = (TextView) v.findViewById(R.id.tv_newspage_title);
            date = (TextView) v.findViewById(R.id.tv_newspage_date);
            img = (NetworkImageView) v.findViewById(R.id.iv_newspage_img);
            comment = (ImageView) v.findViewById(R.id.iv_newspage_comment);
        }
    }

    public void setData(String url) {
        mUrl =  url;
    String path = mHeadPath + url;
        Log.d(TAG, "setData: " + path);
        JsonBeanRequest<NewsItemBean> beanRequest = new JsonBeanRequest<NewsItemBean>(NewsItemBean.class, path,
                new JsonBeanRequestListener<NewsItemBean>() {
                    @Override
                    public void onResponse(NewsItemBean response) {
                        mNewsBean = null;
                        mNewsBean = response.getData().getNews();
                        mNewsItemBean = response;
                        mResUrl = new ArrayList<>();
                        List<NewsItemBean.DataBean.TopnewsBean> topnews = response.getData().getTopnews();
                        for (NewsItemBean.DataBean.TopnewsBean topnew : topnews) {
                            String commenturl = topnew.getTopimage();
                            Log.d(TAG, "onResponse: " + commenturl);
                            mResUrl.add(commenturl);
                        }
                        mFunBanner.setImageUrls(mResUrl);
                        adapter.notifyDataSetChanged();
                    }
                });
       NetworkManager.sRequestQueue.add(beanRequest);
    }
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position>=2){
                String title = mNewsBean.get(position-2).getTitle();
                String url = mNewsBean.get(position-2).getUrl();
                SP.setBooleanData(title,true,getContext());
                adapter.notifyDataSetChanged();
                Intent intent = new Intent(getContext(),WepActivity.class);
                intent.putExtra("webUrl",url);
                getContext().startActivity(intent);
            }




        }
    };
}
